package orm.northwind;

public class Product {
	final String productName;
	final double unitPrice;
	final int unitsInStock;
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
