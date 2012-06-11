package orm.northwind.model;

import orm.Entity;

public class Product implements Entity<Integer>{
	final String productName;
	final double unitPrice;
	final int unitsInStock;
	int id;
	Iterable<OrderDetails> orders;
	
	public Iterable<OrderDetails> getOrders() {
		return orders;
	}
	public void setOrders(Iterable<OrderDetails> orders) {
		this.orders = orders;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product(String productName, double unitPrice, int unitsInStock, Iterable<OrderDetails> orders) {
		super();
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.orders = orders;
	}
	public String getProductName() {
		return productName;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public int getUnitsInStock() {
		return unitsInStock;
	}
	
	
}
