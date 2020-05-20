package net.openu.restapi.api.order;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import net.openu.restapi.account.repository.order.Order;
import net.openu.restapi.account.repository.order.shippingaddress.ShippingAddress;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.api.order
 * User: dean
 * Date: 2020/05/19
 * Time: 6:10 오후
 */
public class OrderDto {


  @Getter
  @Setter
  public static class createReq {

    @NotBlank
    private String mallOrderId;
    private Address address;


    public Order toEntity() {
      return Order.builder()
          .mallOrderId(this.mallOrderId)
          .shippingAddress(this.address.toEntity())
          .build();
    }

  }


  @Getter
  @Setter
  public static class Address {

    private String zipCode;
    private String recipient;

    public ShippingAddress toEntity() {
      return ShippingAddress.builder().recipient(recipient).zipCode(zipCode).build();
    }
  }


}
