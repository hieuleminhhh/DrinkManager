package drinkmanage.example.drinkmanage2.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import drinkmanage.example.drinkmanage2.Model.Drink;
import drinkmanage.example.drinkmanage2.Model.OrderDetail;
import drinkmanage.example.drinkmanage2.Model.Orders;

public interface IOrderDetailService {
	public OrderDetail orderDeatail(long drinkId, long orderId, int totalPrice, int quantity);
	public List<OrderDetail> listOrderDetail();
	public List<OrderDetail> listOrderDetailByStatus(Orders order,String status);
	public List<OrderDetail> listOrderDetailByOrder(Orders order);
	public OrderDetail getOrderDetailById(long id);
	public boolean deleteOrderDetail(long id);
	public Page<OrderDetail> listAllPage(int currentPage);
	public List<Object[]> lisOrderDetailDistinct();
}
