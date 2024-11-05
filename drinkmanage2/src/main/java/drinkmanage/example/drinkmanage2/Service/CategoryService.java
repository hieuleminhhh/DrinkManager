package drinkmanage.example.drinkmanage2.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drinkmanage.example.drinkmanage2.Model.Category;
import drinkmanage.example.drinkmanage2.Repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> listCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}
	

}
