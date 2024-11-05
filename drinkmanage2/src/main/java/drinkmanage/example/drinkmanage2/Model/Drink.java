package drinkmanage.example.drinkmanage2.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="drink")
public class Drink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="drink_id")
	private long drinkId;
	@Column(name="drink_name")
	private String drinkName;
	@Column(name="drink_price")
	private int drinkPrice;
	@Column(name="drink_title")
	private String drinkTitle;
	@Column(name="drink_image")
	private String drinkImage;
	
	@Column(name="category_id", insertable = true, updatable = true)
	private long categoryId;
	
	@Column(name="drink_quantity")
	private int drinkQuantity;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "drink")
	private List<OrderDetail> orderDetails;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", insertable = false, updatable = false)
	private Category category;
	
	public Drink() {
		// TODO Auto-generated constructor stub
	}
	public Drink(long drinkId, String drinkName, int drinkPrice, String drinkTitle, String drinkImage, long categoryId,
			int drinkQuantity, List<OrderDetail> orderDetails, Category category) {
		super();
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.drinkPrice = drinkPrice;
		this.drinkTitle = drinkTitle;
		this.drinkImage = drinkImage;
		this.categoryId = categoryId;
		this.drinkQuantity = drinkQuantity;
		this.orderDetails = orderDetails;
		this.category = category;
	}
	public long getDrinkId() {
		return drinkId;
	}
	public void setDrinkId(long drinkId) {
		this.drinkId = drinkId;
	}
	public String getDrinkName() {
		return drinkName;
	}
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	public int getDrinkPrice() {
		return drinkPrice;
	}
	public void setDrinkPrice(int drinkPrice) {
		this.drinkPrice = drinkPrice;
	}
	public String getDrinkTitle() {
		return drinkTitle;
	}
	public void setDrinkTitle(String drinkTitle) {
		this.drinkTitle = drinkTitle;
	}
	public String getDrinkImage() {
		return drinkImage;
	}
	public void setDrinkImage(String drinkImage) {
		this.drinkImage = drinkImage;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public int getDrinkQuantity() {
		return drinkQuantity;
	}
	public void setDrinkQuantity(int drinkQuantity) {
		this.drinkQuantity = drinkQuantity;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
