package net.openu.restapi.account.service.order;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openu.restapi.account.repository.order.Order;
import net.openu.restapi.account.repository.order.item.LineItem;
import net.openu.restapi.account.repository.order.payment.CreditCardPayment;
import net.openu.restapi.account.repository.order.payment.MobilePhonePayment;
import net.openu.restapi.account.repository.order.payment.OrderRepository;
import net.openu.restapi.account.repository.order.shippingaddress.ShippingAddress;
import net.openu.restapi.account.repository.order.shippingaddress.ShippingAddressRepository;
import net.openu.restapi.api.exception.AlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.account.service.order
 * User: dean
 * Date: 2020/05/19
 * Time: 5:22 오후
 */
@Slf4j

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ShippingAddressRepository shippingAddressRepository;

  @Transactional
  public Order save(Order request) {
    Order order = orderRepository.findByMallOrderId(request.getMallOrderId())
        .orElse(null);
    if (order != null) {
      throw new AlreadyExistsException("이미 존재하는 mallOrderId입니다.");
    }

    request.addLineItem(LineItem.builder().productId("PROD_01").name("상품A").price(1000l).qty(2).build());
    Order savedOrder = orderRepository.save(request);
    //addLineItem

    savedOrder.addPayment(new CreditCardPayment(2000l, "1111-1111-1111-1111"));
    savedOrder.addPayment(new MobilePhonePayment(3000l, "010-1111-2222"));

    return savedOrder;

  }

  @Transactional
  public Long removeShoppingAddress(final Long id) {
    Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문정보입니다."));
    order.getShippingAddress().removeShoppingAddress();
    return id;
  }
}
