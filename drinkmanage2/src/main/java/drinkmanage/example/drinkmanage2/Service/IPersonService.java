package drinkmanage.example.drinkmanage2.Service;

import java.util.List;

import drinkmanage.example.drinkmanage2.Model.Person;

public interface IPersonService {
	public Person login(String email, String password);
	public Person checkUsernameExits(String email);
	public Person signup(String email,String name, String password, String phone, int role);
	public List<Person> listPerson();
	
}
