package net.openu.restapi.account.repository.order.payment;

import static org.junit.jupiter.api.Assertions.*;

import net.openu.restapi.account.repository.order.Order;
import net.openu.restapi.account.repository.order.item.LineItem;
import net.openu.restapi.account.repository.order.shippingaddress.ShippingAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.account.repository.order.payment
 * User: dean
 * Date: 2020/05/19
 * Time: 12:49 오후
 */
@DataJpaTest
class OrderRepositoryOrderController {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  void addOrder(){
//    Order order = new Order(1l);
//    //품목
//    order.addLineItem(LineItem.builder().productId("PROD_01").name("상품A").price(1000l).qty(2).build());
//    order.addLineItem(LineItem.builder().productId("PROD_02").name("상품B").price(3000l).qty(1).build());
//    //결제수단
//    order.addOrderPayment(new CreditCardPayment(2000l, "1111-1111-1111-1111"));
//    order.addOrderPayment(new MobilePhonePayment(3000l, "010-1111-2222"));
//
//    //배송지
//    order.setShippingAddress(new ShippingAddress("22222", "baek byung nam"));
//
//    orderRepository.save(order);



  }
}