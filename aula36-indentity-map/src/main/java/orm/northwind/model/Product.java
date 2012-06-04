package orm.northwind.model;

import orm.Entity;

public class Product implements Entity<Integer>{
	final String productName;
	final double unitPrice;
	final int unitsInStock;
	int id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product(String productName, double unitPrice, int unitsInStock) {
		super();
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
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
