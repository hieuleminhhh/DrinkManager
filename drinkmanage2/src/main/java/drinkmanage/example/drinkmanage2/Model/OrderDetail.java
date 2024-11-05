package drinkmanage.example.drinkmanage2.Model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orders_detail")
@IdClass(OrderDetailId.class)
public class OrderDetail implements Serializable {
    @Id
    @Column(name="drink_id")
    private long drinkId;

    @Id
    @Column(name="order_id")
    private long orderId;
    
    @Column(name="total_price")
    private int totalPrice;
    
    @Column(name="quantity")
    private int quantity;
    
    @ManyToOne
    @JoinColumn(name="order_id", insertable=false, updatable=false)
    private Orders order;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="drink_id", insertable=false, updatable=false)
    private Drink drink;

    public OrderDetail() {
       
    }

    public OrderDetail(long drinkId, long orderId, int totalPrice, int quantity) {
        this.drinkId = drinkId;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

	public long getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(long drinkId) {
		this.drinkId = drinkId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

    // Getters và setters cho các thuộc tính
    
}