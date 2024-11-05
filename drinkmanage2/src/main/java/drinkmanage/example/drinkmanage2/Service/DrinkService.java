package drinkmanage.example.drinkmanage2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import drinkmanage.example.drinkmanage2.Model.Category;
import drinkmanage.example.drinkmanage2.Model.Drink;
import drinkmanage.example.drinkmanage2.Repository.CategoryRepository;
import drinkmanage.example.drinkmanage2.Repository.DrinkRepository;

@Service
public class DrinkService implements IDrinkService {

	@Autowired
	private DrinkRepository drinkRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Drink addDrink(long categoryId, String drinkImage, String drinkName, String drinkTitle, int drinkPrice, int drinkQuantity ) {
		Drink drink = new Drink();
//		Category category = new Category();
//		category.setCategoryId(categoryId);
//		drink.setCategory(category);
		drink.setCategoryId(categoryId);
		drink.setDrinkImage(drinkImage);
		drink.setDrinkName(drinkName);
		drink.setDrinkTitle(drinkTitle);
		drink.setDrinkPrice(drinkPrice);
		drink.setDrinkQuantity(drinkQuantity);
		drinkRepository.save(drink);
		return drink;
	}
	
	@Override
	public Drink updateDrink(long id, Drink drink) {
		if (drink != null) {
			Drink drinks = getDrinkById(id);
			if (drinks != null) {
				drinks.setDrinkName(drink.getDrinkName());
				drinks.setDrinkImage(drink.getDrinkImage());
				drinks.setDrinkTitle(drink.getDrinkTitle());
				drinks.setDrinkPrice(drink.getDrinkPrice());
				return drinkRepository.save(drinks);
			}
		}
		return null;
	}

	@Override
	public boolean deleteDrink(long id) {
		if (id >= 1) {
			Drink drink = getDrinkById(id);
			if (drink != null) {
				drinkRepository.delete(drink);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Drink> listDrink() {
		// TODO Auto-generated method stub
		return drinkRepository.findAll();
	}

	@Override
	public Drink getDrinkById(long id) {
		List<Drink> drinks = listDrink();
		for (Drink drink : drinks) {
			if (drink.getDrinkId() == id) {
				return drink;
			}
		}
		return null;
	}

	@Override
	public List<Drink> getDrinkByName(String name) {
		// TODO Auto-generated method stub
		return drinkRepository.findBydrinkNameContainingIgnoreCase(name);
	}

	@Override
	public Page<Drink> listAllPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return drinkRepository.findAll(pageable);
	}

	@Override
	public List<Drink> listDrinkAll() {
		// TODO Auto-generated method stub
		return drinkRepository.listDrinkAll();
	}

	@Override
	public Drink addDrinkAPI(Drink drink) {
		if(drink!=null) {
			return drinkRepository.save(drink);
		}
		return null;
	}

	@Override
	public Page<Drink> searchByName(String searchName, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return drinkRepository.findByDrinkNameContaining(searchName, pageable);
	}

	
	

}
