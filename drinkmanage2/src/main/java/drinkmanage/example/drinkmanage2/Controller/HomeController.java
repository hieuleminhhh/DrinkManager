package drinkmanage.example.drinkmanage2.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import drinkmanage.example.drinkmanage2.Model.Drink;
import drinkmanage.example.drinkmanage2.Model.Orders;
import drinkmanage.example.drinkmanage2.Service.IDrinkService;
import drinkmanage.example.drinkmanage2.Service.IOrderService;

@Controller
public class HomeController {
	@Autowired
	private IDrinkService iDrinkService;
	@Autowired
	private IOrderService iOrderService;
	@RequestMapping("/home")
	@GetMapping
	public String listDrink(Model model, @RequestParam(value="searchname", required=false) String searchName,
	                        @RequestParam(value="page", required=false, defaultValue="1") Integer pageNumber) {
	    if (pageNumber == null || pageNumber < 1) {
	        pageNumber = 1;
	    }
	    Page<Drink> drinkPage;
	    int totalPages;

	    
	    if (searchName == null || searchName.isEmpty()) {
	        drinkPage = iDrinkService.listAllPage(pageNumber);
	        totalPages = drinkPage.getTotalPages();
	    } else { 
	    	
	        drinkPage = iDrinkService.searchByName(searchName, pageNumber);
	        totalPages = drinkPage.getTotalPages();
	        model.addAttribute("searchname", searchName);
	    }
	    List<Orders> listOrders = iOrderService.listOrderByStatus("0");
		int num=0;
		for (Orders orders : listOrders) {
			num=listOrders.size();
		}
		model.addAttribute("num", num);
	    List<Drink> listDrinkPage = drinkPage.getContent();
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("listDrink", listDrinkPage);
	    model.addAttribute("currentPage", pageNumber);
	    return "drink";
	}
//	@RequestMapping("/search")
//	@GetMapping
//	public String search(@RequestParam("searchname") String name, Model model) {
//		List<Drink> listDrinkByName = iDrinkService.getDrinkByName(name);	
//		int total = 0;
//		for (Drink drink : listDrinkByName) {
//			if(drink.getDrinkName().equals(name)) {
//				total+=listDrinkByName.size();
//			}
//			
//		}
//		
//		Page<Drink> page = iDrinkService.listAllPage(total);
//		int totalPages = page.getTotalPages();
//		model.addAttribute("totalPages", 1);
//		model.addAttribute("page", page);
//		model.addAttribute("listDrink", listDrinkByName);
//		
//		
//		
//		
//		return "drink";
//	}
    

}