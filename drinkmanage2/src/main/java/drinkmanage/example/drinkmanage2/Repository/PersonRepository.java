package drinkmanage.example.drinkmanage2.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import drinkmanage.example.drinkmanage2.Model.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	Person findBypersonEmail(String email);
	@Query(value="select * from person", nativeQuery = true)
	List<Person> litsPerson();
	
}