package orm.northwind.model;

import orm.Entity;

/**
 * @author  mcarvalho
 */
public class OrderDetails implements Entity<Integer>{
	public int orderId;
	public int productId;
	private double unitPrice;
	private int quantity;
	private double discount;

	public OrderDetails(
			int orderId,
			int productId,
			double unitPrice, 
			int quantity, 
			double discount) {
		this.orderId = orderId;
		this.productId = productId;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
	}

	public Integer getId() {
		return orderId;
	}

	public void setId(Integer id) {
		this.orderId= id;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
