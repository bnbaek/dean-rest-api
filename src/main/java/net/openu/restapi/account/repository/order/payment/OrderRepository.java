package net.openu.restapi.account.repository.order.payment;

import java.util.Optional;
import net.openu.restapi.account.repository.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.account.repository.order.payment
 * User: dean
 * Date: 2020/05/19
 * Time: 12:49 오후
 */
public interface OrderRepository extends JpaRepository<Order, Long> {


  Optional<Order> findByMallOrderId(String mallOrderId);
}
