package drinkmanage.example.drinkmanage2.Service;

import java.util.List;


import drinkmanage.example.drinkmanage2.Model.Orders;

public interface IOrderService {
	public Orders oderDrink(int quantity, String status, int total_price, int person_id);
	public List<Orders> listOrder();
	public boolean deleteOrder(long orderId);
	public Orders listOrderById(long id);
	public void deleteOrderByPersonId(long personId);
	public List<Orders> listOrderByPersonId(long personId);
	public Orders updateOrder(long id, Orders order);
	public Orders getOrderById(long id);
	public List<Orders> listOrderByStatus(String status);
}
