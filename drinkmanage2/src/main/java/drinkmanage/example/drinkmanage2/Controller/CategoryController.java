package drinkmanage.example.drinkmanage2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import drinkmanage.example.drinkmanage2.Service.ICategoryService;
@RequestMapping("/category")
@Controller
public class CategoryController {
	@Autowired
	private ICategoryService iCategoryService;
	
	
	
}
