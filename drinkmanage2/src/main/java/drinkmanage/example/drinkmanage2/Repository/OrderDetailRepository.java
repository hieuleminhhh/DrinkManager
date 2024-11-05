package drinkmanage.example.drinkmanage2.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import drinkmanage.example.drinkmanage2.Model.OrderDetail;
import drinkmanage.example.drinkmanage2.Model.Orders;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	List<OrderDetail> findByOrder(Orders order);
	Page<OrderDetail> findAll(Pageable pageable);
	
// @Query(value = "SELECT drink.drink_name, SUM(orders_detail.quantity) AS quantity, SUM(orders_detail.total_price) AS total_price FROM orders_detail "
// 		+ "INNER JOIN drink ON orders_detail.drink_id = drink.drink_id GROUP BY orders_detail.drink_id;",nativeQuery = true)
//	@Query(value="Select * from orders_detail", nativeQuery=true)
	 @Query(value= "SELECT drink.drink_name, SUM(orders_detail.quantity) AS quantity, SUM(orders_detail.total_price) AS total_price FROM orders_detail INNER JOIN drink ON orders_detail.drink_id = drink.drink_id GROUP BY orders_detail.drink_id",nativeQuery = true)
	    List<Object[]> findOrderDetails();
}
