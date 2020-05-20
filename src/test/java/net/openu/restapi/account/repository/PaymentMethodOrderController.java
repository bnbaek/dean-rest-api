package net.openu.restapi.account.repository;

import static org.assertj.core.api.Assertions.assertThat;

import net.openu.restapi.account.repository.order.payment.PaymentMethod;
import org.junit.jupiter.api.Test;

class PaymentMethodOrderController {

  @Test
  void enumPaymentMethodTest() {
    PaymentMethod mobilePhone = PaymentMethod.ofId("MOBILE_PHONE");
    System.out.println(mobilePhone.getId());
    assertThat(mobilePhone.getId()).isEqualTo(PaymentMethod.MOBILE_PHONE.getId());
    assertThat(mobilePhone).isEqualTo(PaymentMethod.MOBILE_PHONE);
  }
}