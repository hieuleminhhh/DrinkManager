package drinkmanage.example.drinkmanage2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drinkmanage.example.drinkmanage2.Model.Person;
import drinkmanage.example.drinkmanage2.Repository.PersonRepository;

@Service
public class PersonService implements IPersonService {
	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person login(String email, String password) {
		Person person = personRepository.findBypersonEmail(email);
		if(person==null) {
			return null;
		}
		if(!person.getPersonPassword().equals(password)) {
			return null;
		}
		return person;
	}

	@Override
	public Person checkUsernameExits(String email) {
		Person p = personRepository.findBypersonEmail(email);
		return p;
	}

	@Override
	public Person signup(String email,String name, String password,String phone, int role) {
		Person person = new Person();
		person.setPersonEmail(email);
		person.setPersonName(name);
		person.setPersonPassword(password);
		person.setPersonPhone(phone);
		person.setPersonRole(role);
		personRepository.save(person);
		return person;
	}

	@Override
	public List<Person> listPerson() {
		return personRepository.findAll();
	}
	

}
