package net.openu.restapi.account.repository.order.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.openu.restapi.account.repository.order.Order;
import net.openu.restapi.account.repository.order.shippingaddress.ShippingAddress;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.account.repository
 * User: dean
 * Date: 2020/05/19
 * Time: 10:04 오전
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "line_items")
public class LineItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "line_item_id")
  private Long id;
  private String productId;
  private String name;
  private Long price;
  private Integer qty;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "order_id")
  private Order order;

  @Builder
  public LineItem(String productId, String name, Long price, Integer qty) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.qty = qty;
  }

  public void setOrder(Order order) {
    this.order = order;
  }


}
