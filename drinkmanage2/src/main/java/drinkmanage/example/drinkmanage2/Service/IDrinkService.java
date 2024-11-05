package drinkmanage.example.drinkmanage2.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import drinkmanage.example.drinkmanage2.Model.Drink;

public interface IDrinkService {
	public Drink addDrink(long categoryId, String drinkImage, String drinkName, String drinkTitle, int drinkPrice, int drinkQuantity );
	public Drink updateDrink(long id, Drink drink);
	public boolean deleteDrink(long id);
	public List<Drink> listDrink();
	public Drink getDrinkById(long id);
	public List<Drink> getDrinkByName(String name);
	public Page<Drink> listAllPage(int currentPage);
	public List<Drink> listDrinkAll();
	public Drink addDrinkAPI(Drink drink);
	public Page<Drink> searchByName(String searchName, int currentPage);
}
