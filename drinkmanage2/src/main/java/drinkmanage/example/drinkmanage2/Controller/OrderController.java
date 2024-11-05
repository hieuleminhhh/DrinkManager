package drinkmanage.example.drinkmanage2.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import drinkmanage.example.drinkmanage2.Model.Drink;
import drinkmanage.example.drinkmanage2.Model.OrderDetail;
import drinkmanage.example.drinkmanage2.Model.Orders;
import drinkmanage.example.drinkmanage2.Model.Person;
import drinkmanage.example.drinkmanage2.Service.IDrinkService;
import drinkmanage.example.drinkmanage2.Service.IOrderDetailService;
import drinkmanage.example.drinkmanage2.Service.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private IOrderService iOrderService;
	@Autowired
	private IDrinkService iDrinkService;
	@Autowired
	private IOrderDetailService iOrderDetailService;

	@RequestMapping("/{id}")
	@PostMapping
	public String orderDrink(Model model, @PathVariable("id") long id, @SessionAttribute("person") Person person) {
		Drink drink = iDrinkService.getDrinkById(id);
		int total = drink.getDrinkQuantity() * drink.getDrinkPrice();
		Orders orders = iOrderService.oderDrink(drink.getDrinkQuantity(), "0", total, person.getPersonId());
		iOrderDetailService.orderDeatail(drink.getDrinkId(), orders.getOrderId(), total, drink.getDrinkQuantity());
		drink.setDrinkQuantity(1);
		iDrinkService.updateDrink(id, drink);
		return "redirect:/drink";
	}

	@GetMapping
	public String listDrinkOrder(Model model) {
		List<Orders> listOrder = iOrderService.listOrderByStatus("0");

		int totalAll = 0;
		for (Orders orders : listOrder) {
			if (orders.getStatus().equals("Processing")) {
				totalAll += orders.getTotalPrice();

			}

			model.addAttribute("totalAll", totalAll);
		}
		model.addAttribute("listOrder", listOrder);
		return "order";

	}

	@RequestMapping("/pay/{id}")
	@PostMapping
	public String pay(@PathVariable("id") long id) {
		Orders order = iOrderService.getOrderById(id);
		if (order != null) {
			order.setStatus("1");
			iOrderService.updateOrder(id, order);
		}
		return "redirect:/order";
	}

	@RequestMapping("/payall")
	public String payAll() {
		List<Orders> listOrder = iOrderService.listOrderByStatus("0");
		for (Orders orders : listOrder) {
			orders.setStatus("1");
			iOrderService.updateOrder(orders.getOrderId(), orders);
		}
		return "redirect:/order";
	}

	@RequestMapping("/delete/{id}")
	@PostMapping
	public String delete(@PathVariable("id") long id) {
		OrderDetail od = iOrderDetailService.getOrderDetailById(id);
		if (od != null) {
			iOrderDetailService.deleteOrderDetail(id);
		}
		Orders order = iOrderService.getOrderById(id);
		if (order != null) {
			iOrderService.deleteOrder(id);
		}

		return "redirect:/order";
	}

	@RequestMapping("/deleteall")
	public String deleteAll() {
		List<OrderDetail> listOrderDetail = iOrderDetailService.listOrderDetail();
		for (OrderDetail orderDetail : listOrderDetail) {
			if (orderDetail.getOrder().getStatus().equals("Processing")) {
				iOrderDetailService.deleteOrderDetail(orderDetail.getOrder().getOrderId());
			}
		}
		List<Orders> listOrder = iOrderService.listOrderByStatus("0");
		for (Orders orders : listOrder) {
			iOrderService.deleteOrder(orders.getOrderId());
		}
		return "redirect:/order";
	}

	@RequestMapping("/back")
	@PostMapping
	public String back() {
		return "redirect:/drink";
	}

}
