package drinkmanage.example.drinkmanage2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import drinkmanage.example.drinkmanage2.Model.OrderDetail;
import drinkmanage.example.drinkmanage2.Model.Orders;
import drinkmanage.example.drinkmanage2.Repository.OrderDetailRepository;

@Service
public class OrderDetailService implements IOrderDetailService {
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public OrderDetail orderDeatail(long drinkId, long orderId, int totalPrice, int quantity) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDrinkId(drinkId);
		orderDetail.setOrderId(orderId);
		orderDetail.setTotalPrice(totalPrice);
		orderDetail.setQuantity(quantity);
		orderDetailRepository.save(orderDetail);
		return orderDetail;
	}
	@Override
	public List<OrderDetail> listOrderDetail() {
		return orderDetailRepository.findAll();
	}
	@Override
	public List<OrderDetail> listOrderDetailByStatus(Orders order, String status) {
		
		return null;
	}
	@Override
	public List<OrderDetail> listOrderDetailByOrder(Orders order) {
		
		return orderDetailRepository.findByOrder(order);
	}
	@Override
	public OrderDetail getOrderDetailById(long id) {
		List<OrderDetail> listOrderDetails = listOrderDetail();
		for (OrderDetail orderDetail : listOrderDetails) {
			if(orderDetail.getOrderId()==id) {
				return orderDetail;
			}
		}
		return null;
	}
	@Override
	public boolean deleteOrderDetail(long id) {
		if(id>=1) {
			OrderDetail od = getOrderDetailById(id);
			if(od!=null) {
				orderDetailRepository.delete(od);
				return true;
			}
		}
		return false;
		
	}
	@Override
	public Page<OrderDetail> listAllPage(int currentPage) {
		Pageable pageable = PageRequest.of(currentPage-1, 5);
		return orderDetailRepository.findAll(pageable);
	}
	@Override
	public List<Object[]> lisOrderDetailDistinct() {
		return orderDetailRepository.findOrderDetails();
	}
}
