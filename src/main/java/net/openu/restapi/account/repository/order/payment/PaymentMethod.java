package net.openu.restapi.account.repository.order.payment;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum PaymentMethod {
  CREDIT_CARD(Values.CREDIT_CARD), MOBILE_PHONE(Values.MOBILE_PHONE);

  private String id;

  PaymentMethod(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public static class Values {

    public static final String CREDIT_CARD = "CREDITCARD";
    public static final String MOBILE_PHONE = "MOBILEPHONE";
  }

  public static PaymentMethod ofId(String id) {
    return Arrays.stream(PaymentMethod.values())
        .filter(v -> v.getId().equalsIgnoreCase(id))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("Incorrect use of PaymentMethod"))
        ;
  }

}
