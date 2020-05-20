package net.openu.restapi.account.repository.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.openu.restapi.account.repository.order.item.LineItem;
import net.openu.restapi.account.repository.order.payment.OrderPayment;
import net.openu.restapi.account.repository.order.shippingaddress.ShippingAddress;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(Include.NON_NULL)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;
  private LocalDateTime createdAt;
  @Version
  private int version;

  private String mallOrderId;

  @JsonManagedReference
  @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<LineItem> lineItems = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<OrderPayment> orderPayments = new ArrayList<>();


  @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private ShippingAddress shippingAddress;

  public Order(String mallOrderId) {
    this.mallOrderId = mallOrderId;
    this.createdAt = LocalDateTime.now();
  }

  @Builder
  public Order(String mallOrderId, ShippingAddress shippingAddress) {
    this.mallOrderId = mallOrderId;
    this.applyShippingAddress(shippingAddress);
  }

  public void applyShippingAddress(final ShippingAddress shippingAddress) {
    this.shippingAddress = shippingAddress;
    shippingAddress.setOrder(this);
  }

  public void addPayment(OrderPayment payment) {
    this.orderPayments.add(payment);
    payment.setOrder(this);
  }


  public void addLineItem(final LineItem lineItem) {
    this.lineItems.add(lineItem);
    lineItem.setOrder(this);
  }
}
