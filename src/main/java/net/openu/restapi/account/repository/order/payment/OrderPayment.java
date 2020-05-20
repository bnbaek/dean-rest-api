package net.openu.restapi.account.repository.order.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.openu.restapi.account.repository.order.Order;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "order")

@Entity
@Table(name = "order_payments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "method", discriminatorType = DiscriminatorType.STRING)
public abstract class OrderPayment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_payment_id", updatable = false)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "method", nullable = false, updatable = false, insertable = false)
  private PaymentMethod method;

  private Long amount;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "order_id")
  private Order order;

  public OrderPayment(Long amount) {
    this.amount = amount;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
