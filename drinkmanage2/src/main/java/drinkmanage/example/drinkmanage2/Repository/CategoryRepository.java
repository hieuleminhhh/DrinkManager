package drinkmanage.example.drinkmanage2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drinkmanage.example.drinkmanage2.Model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
