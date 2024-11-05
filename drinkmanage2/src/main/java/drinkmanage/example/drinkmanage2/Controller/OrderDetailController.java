package drinkmanage.example.drinkmanage2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import drinkmanage.example.drinkmanage2.Model.OrderDetail;
import drinkmanage.example.drinkmanage2.Model.Person;
import drinkmanage.example.drinkmanage2.Service.IOrderDetailService;

@Controller
@RequestMapping("/odetail")
public class OrderDetailController {

	@Autowired  
	private IOrderDetailService iOrderDetailService;
	@GetMapping
	public String listOrderDetail(Model model,  @SessionAttribute(name = "person") Person person) {
		return listByPage(model, 1, person);
		
	}
	@GetMapping("/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage, @SessionAttribute(name = "person") Person person) {
		Page<OrderDetail> page = iOrderDetailService.listAllPage(currentPage);
		int totalPages = page.getTotalPages();
		List<OrderDetail> listOrderDetailPage = page.getContent();
		List<OrderDetail> listOrderDetail = iOrderDetailService.listOrderDetail();
		int total = 0;
		int upaid = 0;
		int paid = 0;
		for (OrderDetail orderDetail : listOrderDetail) {
			total+=orderDetail.getTotalPrice();
			if(orderDetail.getOrder().getStatus().equals("Processing")) {
				upaid+=orderDetail.getTotalPrice();
			}else {
				paid+=orderDetail.getTotalPrice();
			}
			
		}
		model.addAttribute("upaid", upaid);
		model.addAttribute("paid", paid);
		model.addAttribute("totalAll", total);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listOrderDetail",listOrderDetailPage);
		return "manageorder";
	}
	@GetMapping("/totalOrder")
	public String listOrderDetail(Model model) {
		List<Object[]> totalOrder = iOrderDetailService.lisOrderDetailDistinct();
		int totalMoney = 0;
		int totalQuantity = 0;
		for (Object[] objects : totalOrder) {
			totalMoney+=Integer.parseInt(objects[2].toString());
			totalQuantity+=Integer.parseInt(objects[1].toString());
		}
		model.addAttribute("totalQuantity", totalQuantity);
		model.addAttribute("totalMoney", totalMoney);
		model.addAttribute("totalOrder", totalOrder);
		return "totalorder";
	}
//	
	@RequestMapping("/back")
	@PostMapping
	public String back() {
		return "redirect:/drink";
	}
	
}
