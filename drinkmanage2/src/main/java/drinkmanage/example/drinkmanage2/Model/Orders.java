package drinkmanage.example.drinkmanage2.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderId;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "status")
	private String status;
	@Column(name = "total_price")
	private int totalPrice;
	@Column(name = "person_id", insertable = false, updatable = false)
	private long personId;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;

	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public Orders(long orderId, int quantity, String status, int totalPrice, long personId, Person person,
			List<OrderDetail> orderDetails) {
		super();
		this.orderId = orderId;
		this.quantity = quantity;
		this.status = status;
		this.totalPrice = totalPrice;
		this.personId = personId;
		this.person = person;
		this.orderDetails = orderDetails;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		if(status.equals("0")) {
			return "Processing";
		}
		return "Done";
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	

}
