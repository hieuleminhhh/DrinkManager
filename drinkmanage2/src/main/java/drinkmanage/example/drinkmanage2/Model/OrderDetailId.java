package drinkmanage.example.drinkmanage2.Model;

import java.io.Serializable;

import javax.persistence.Column;

public class OrderDetailId implements Serializable {
	    private long drinkId;
	    private long orderId;
	    public OrderDetailId() {
			// TODO Auto-generated constructor stub
		}
		public OrderDetailId(long drinkId, long orderId) {
			super();
			this.drinkId = drinkId;
			this.orderId = orderId;
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

	    // Constructors, getters, setters
	
}
