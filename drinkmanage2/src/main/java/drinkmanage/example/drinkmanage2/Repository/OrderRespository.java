package drinkmanage.example.drinkmanage2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drinkmanage.example.drinkmanage2.Model.Orders;

@Repository
public interface OrderRespository extends JpaRepository<Orders, Long> {
	
//	@Query("SELECT o.orderId, p.personEmail, d.drinkName, d.drinkImage, o.quantity, o.totalPrice, o.status \r\n"
//			+ "FROM Orders o \r\n"
//			+ "INNER JOIN Person p ON o.personId = p.personId\r\n"
//			+ "INNER JOIN OrderDetail od ON o.orderId = od.orderId\r\n"
//			+ "INNER JOIN Drink d ON od.drinkId = d.drinkId")
//	List<Orders> listAllOrder();
	void deleteAllBypersonId(long personId);
	List<Orders> findBypersonId(long personId);
	List<Orders> findByStatus(String status);
}
