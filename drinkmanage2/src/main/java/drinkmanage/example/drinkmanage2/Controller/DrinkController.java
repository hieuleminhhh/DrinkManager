package drinkmanage.example.drinkmanage2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import drinkmanage.example.drinkmanage2.Model.Category;
import drinkmanage.example.drinkmanage2.Model.Drink;
import drinkmanage.example.drinkmanage2.Model.Orders;
import drinkmanage.example.drinkmanage2.Model.Person;
import drinkmanage.example.drinkmanage2.Service.ICategoryService;
import drinkmanage.example.drinkmanage2.Service.IDrinkService;
import drinkmanage.example.drinkmanage2.Service.IOrderService;

@Controller
@RequestMapping("/drink")
public class DrinkController {
	@Autowired
	private IDrinkService iDrinkService;
	@Autowired
	private ICategoryService iCategoryService;
	@Autowired
	private IOrderService iOrderService;

	@GetMapping
	public String listDrink(Model model, @RequestParam(value="searchname", required=false) String searchName,
	                        @RequestParam(value="page", required=false, defaultValue="1") Integer pageNumber, @SessionAttribute(name = "person") Person person) {		
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
	@GetMapping("/quantity+")
	public String moreQuantity(@RequestParam("id") Long id,
	        @RequestParam(value = "searchname", required = false) String searchName,
	        @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNumber,
	        Model model) {
	    Drink drink = iDrinkService.getDrinkById(id);
	    if (drink != null) {
	        drink.setDrinkQuantity(drink.getDrinkQuantity()+1);
	        iDrinkService.updateDrink(id, drink);
	    }
	    model.addAttribute("page", pageNumber);
	    return "redirect:/drink?searchname=" + searchName + "&page=" + pageNumber;
	}
	@GetMapping("/quantity-/{id}/{pageNumber}")
	public String lessQuantity(@PathVariable("id") Long id) {
		Drink drink = iDrinkService.getDrinkById(id);
		if(drink != null&&drink.getDrinkQuantity()>1) {
			drink.setDrinkQuantity(drink.getDrinkQuantity()-1);
			iDrinkService.updateDrink(id, drink);
		}
		return "redirect:/{pageNumber}";
	}
	@RequestMapping("/refresh")
	@PostMapping
	public String refresh() {
		List<Drink> listDrink = iDrinkService.listDrink();
		for (Drink drink : listDrink) {
			drink.setDrinkQuantity(1);
			iDrinkService.updateDrink(listDrink.size(), drink);
		}
		
		return "redirect:/drink";
	}
	@GetMapping("/manage")
	public String manageDrink(Model model, @RequestParam(value="searchname", required=false) String searchName,
	                        @RequestParam(value="page", required=false, defaultValue="1") Integer pageNumber, @SessionAttribute(name = "person") Person person) {		
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
	    model.addAttribute("listDrinkManage", listDrinkPage);
	    model.addAttribute("currentPage", pageNumber);
	    return "managedrink";
	}
	
	@GetMapping("/manage/add")
	public String add(Model model) {
		Drink drink = new Drink();
		List<Category> listCategory = iCategoryService.listCategory();
		model.addAttribute("categories", listCategory);
		model.addAttribute("drink", drink);
		return"add";
	}
	@RequestMapping("/manage/add")
	@PostMapping
	public String save(@RequestParam("category") String categoryId,@RequestParam("name") String name, @RequestParam("title") String title, @RequestParam("price") int price, @RequestParam("image") String image) {
		long id = Long.parseLong(categoryId);
		iDrinkService.addDrink(id, image, name, title, price, 1);
		return "redirect:/drink";
		
	}
	@RequestMapping("/update/{id}")
	@GetMapping
	public String update(@PathVariable("id") long id, Model model) {
		Drink drink = iDrinkService.getDrinkById(id);
		model.addAttribute("drink", drink);
		return "update";
		
	}
	@RequestMapping("/update")
	@PostMapping
	public String save(@ModelAttribute("drink") Drink drink, @RequestParam("drinkId") Long id ) {
		iDrinkService.updateDrink(id, drink);
		return "redirect:/drink";
		
	}
	@RequestMapping("/delete/{id}")
	@PostMapping
	public String delete(@PathVariable("id") long id) {
		iDrinkService.deleteDrink(id);
		return "redirect:/drink";
	}
	@RequestMapping("/back")
	@PostMapping
	public String back() {
		return "redirect:/drink";
	}
	@RequestMapping("/backlog")
	@PostMapping
	public String backlog() {
		return "redirect:/home";
	}
	
}
