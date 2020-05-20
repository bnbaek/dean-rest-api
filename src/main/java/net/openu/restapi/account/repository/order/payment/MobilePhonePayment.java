package net.openu.restapi.account.repository.order.payment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = PaymentMethod.Values.MOBILE_PHONE)
public class MobilePhonePayment extends OrderPayment {

  private String phoneNumber;

  @Builder
  public MobilePhonePayment(Long amount, String phoneNumber) {
    super(amount);
    this.phoneNumber = phoneNumber;
  }
}
