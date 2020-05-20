package net.openu.restapi.api.order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openu.restapi.account.repository.order.Order;
import net.openu.restapi.account.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

  private final OrderService orderService;


  @GetMapping
  public List<Order> getOrders() {
//    return orderRepository.findAll();
    return null;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Order addOrder(@RequestBody OrderDto.createReq request) {
    return orderService.save(request.toEntity());
  }

  @DeleteMapping("/{id}/shoppingAddress")
  public Long remove(@PathVariable Long id) {
    return orderService.removeShoppingAddress(id);
  }

//    ShippingAddress address = new ShippingAddress("22222", "baek byung nam");
//    Order order = new Order(1l);
//    shippingAddressRepository.save(address);
//    order.setShippingAddress(address);
//    Order save = orderRepository.save(order);

//    Order save1 = orderRepository.save(order);
//    address.setOrder(save1);
//    ShippingAddress save = shippingAddressRepository.save(address);
//    Order save = orderRepository.save(order);

//    Order save = orderRepository.save(order);
//    address.setOrder(save);
//    shippingAddressRepository.save(address);

//    order.applyShoppingAddress(address);

  //배송지
//    order.setShippingAddress(address);
//    shippingAddressRepository.save(address);

//    orderRepository.save(order);

  //품목
//    save.addLineItem(LineItem.builder().productId("PROD_01").name("상품A").price(1000l).qty(2).build());
//    save.addLineItem(LineItem.builder().productId("PROD_02").name("상품B").price(3000l).qty(1).build());
//    //결제수단
//    save.addOrderPayment(new CreditCardPayment(2000l, "1111-1111-1111-1111"));
//    save.addOrderPayment(new MobilePhonePayment(3000l, "010-1111-2222"));
//    return orderRepository.save(order);

//    return order;

//    ShippingAddress address = new ShippingAddress("22222", "baek byung nam");
//    Order order = new Order(1l);
//    shippingAddressRepository.save(address);
//    order.setShippingAddress(address);
//    Order save = orderRepository.save(order);

//    Order save1 = orderRepository.save(order);
//    address.setOrder(save1);
//    ShippingAddress save = shippingAddressRepository.save(address);
//    Order save = orderRepository.save(order);

//    Order save = orderRepository.save(order);
//    address.setOrder(order);
//    shippingAddressRepository.save(address);

  //배송지
//    order.setShippingAddress(address);
//    shippingAddressRepository.save(address);

//    Order save = orderRepository.save(order);

  //품목
//    save.addLineItem(LineItem.builder().productId("PROD_01").name("상품A").price(1000l).qty(2).build());
//    save.addLineItem(LineItem.builder().productId("PROD_02").name("상품B").price(3000l).qty(1).build());
//    //결제수단
//    save.addOrderPayment(new CreditCardPayment(2000l, "1111-1111-1111-1111"));
//    save.addOrderPayment(new MobilePhonePayment(3000l, "010-1111-2222"));
//    return orderRepository.save(order);


}
