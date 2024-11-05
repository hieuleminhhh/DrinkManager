package drinkmanage.example.drinkmanage2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drinkmanage.example.drinkmanage2.Model.Orders;
import drinkmanage.example.drinkmanage2.Model.Person;
import drinkmanage.example.drinkmanage2.Repository.OrderRespository;

@Service
public class OrderService implements IOrderService {
	@Autowired
	private OrderRespository orderRespository;

	@Override
	public Orders oderDrink(int quantity, String status, int total_price, int person_id) {
		Orders order = new Orders();
		order.setQuantity(quantity);
		order.setStatus(status);
		order.setTotalPrice(total_price);
		Person person = new Person();
		person.setPersonId(person_id);
		order.setPerson(person);
		orderRespository.save(order);
		return order;
	}


	@Override
	public List<Orders> listOrder() {		
		return orderRespository.findAll();
	}


	@Override
	public boolean deleteOrder(long orderId) {
		if(orderId>=1) {
			Orders orders = listOrderById(orderId);
			if(orders!=null) {
				orderRespository.delete(orders);
				return true;
			}
		}
		return false;
	}


	@Override
	public Orders listOrderById(long id) {
		List<Orders> listOrders = listOrder();
		for (Orders orders : listOrders) {
			if(orders.getOrderId()==id)
				return orders;
		}
		return null;
	}


	@Override
	public void deleteOrderByPersonId(long personId) {
		List<Orders> orderList = orderRespository.findBypersonId(personId);
		orderRespository.deleteAll(orderList);
		
	}


	@Override
	public List<Orders> listOrderByPersonId(long personId) {
		
		return orderRespository.findBypersonId(personId);
	}


	@Override
	public Orders updateOrder(long id, Orders order) {
		if(order!=null) {
			Orders orders = getOrderById(id);
			if(orders!=null) {
				orders.setStatus(order.getStatus());
				orders.setQuantity(order.getQuantity());
				orders.setTotalPrice(orders.getTotalPrice());
				return orderRespository.save(orders);
			}
		}
		return null;
	}


	@Override
	public Orders getOrderById(long id) {
		List<Orders> listOrders = listOrder();
		for (Orders orders : listOrders) {
			if(orders.getOrderId()==id)
				return orders;
		}
		return null;
	}


	@Override
	public List<Orders> listOrderByStatus(String status) {
		
		return orderRespository.findByStatus(status);
	}





	
}
