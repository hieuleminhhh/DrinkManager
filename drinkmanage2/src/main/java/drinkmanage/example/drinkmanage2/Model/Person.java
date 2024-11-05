package drinkmanage.example.drinkmanage2.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="person_id")
	private int personId;
	@Column(name="person_email")
	private String personEmail;
	@Column(name="person_name")
	private String personName;
	@Column(name="person_password")
	private String personPassword;
	@Column(name="person_phone")
	private String personPhone;
	@Column(name="person_role")
	private int personRole;
	@OneToMany(mappedBy = "person")
	private List<Orders> orders;
	public Person() {
		// TODO Auto-generated constructor stub
	}
	public Person(int personId, String personEmail, String personName, String personPassword, String personPhone,
			int personRole) {
		super();
		this.personId = personId;
		this.personEmail = personEmail;
		this.personName = personName;
		this.personPassword = personPassword;
		this.personPhone = personPhone;
		this.personRole = personRole;
	}

	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonPassword() {
		return personPassword;
	}
	public void setPersonPassword(String personPassword) {
		this.personPassword = personPassword;
	}
	public String getPersonPhone() {
		return personPhone;
	}
	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}
	public int getPersonRole() {
		return personRole;
	}
	public void setPersonRole(int personRole) {
		this.personRole = personRole;
	}

	
	
}
