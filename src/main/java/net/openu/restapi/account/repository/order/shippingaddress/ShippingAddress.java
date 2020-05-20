package net.openu.restapi.account.repository.order.shippingaddress;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.openu.restapi.account.repository.order.Order;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "order")

@Entity
@Table(name = "shipping_address")
public class ShippingAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "shipping_address_id")
  private Long id;

  private String zipCode;
  private String recipient;
  private Boolean isUsed;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
  private Order order;

  @Builder
  public ShippingAddress(String zipCode, String recipient) {
    this.zipCode = zipCode;
    this.recipient = recipient;
    this.isUsed = true;
  }

  public void removeShoppingAddress() {
    this.zipCode = null;
    this.recipient = null;
    this.isUsed = false;
  }

  public void setOrder(final Order order) {
    this.order = order;
  }


}
