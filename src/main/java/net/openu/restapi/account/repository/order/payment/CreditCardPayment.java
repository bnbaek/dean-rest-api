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
@DiscriminatorValue(value = PaymentMethod.Values.CREDIT_CARD)
public class CreditCardPayment extends OrderPayment {

  private String cardNumber;

  @Builder
  public CreditCardPayment(Long amount, String cardNumber) {
    super(amount);
    this.cardNumber = cardNumber;
  }
}
