package drinkmanage.example.drinkmanage2.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private long categoryId;
	@Column(name="category_name")
	private String categoryName;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
	private List<Drink> drinks;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(long categoryId, String categoryName, List<Drink> drinks) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.drinks = drinks;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}

}
