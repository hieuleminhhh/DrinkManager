package drinkmanage.example.drinkmanage2.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import drinkmanage.example.drinkmanage2.Model.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
	List<Drink> findBydrinkNameContainingIgnoreCase(String name);
	Page<Drink> findAll(Pageable pageable);
	Page<Drink> findByDrinkNameContaining(String searchName, Pageable pageable);
	
	@Query(value="CALL getListDrink()", nativeQuery=true)
	List<Drink> listDrinkAll();
}
