package orm.northwind.test;

import java.sql.SQLException;

import org.junit.Test;

import junit.framework.Assert;


import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcConverter;
import orm.JdbcExecutor;
import orm.northwind.Employee;
import orm.northwind.EmployeesDataMapper;
import orm.northwind.Product;
import orm.northwind.ProductsDataMapper;

import app.CfgModule;

import com.google.inject.Guice;

public class TestProducts {
	ProductsDataMapper prodMapper;
	
	public TestProducts(){
		prodMapper = Guice.createInjector(new CfgModule()).getInstance(ProductsDataMapper.class);
	}

	@Test
	public void test_load_all_employees() throws SQLException{
		Iterable<Product> res = prodMapper.loadAll();
		int size = 0;
		for(Product e:res){size++;}
		Assert.assertEquals(77, size);
	}
	@Test
	public void test_load_byid_employees() throws SQLException{
		Product e = prodMapper.loadById(7);
		Assert.assertEquals("Uncle Bob's Organic Dried Pears", e.getProductName());
		Assert.assertEquals(30.0, e.getUnitPrice());
		Assert.assertEquals(15, e.getUnitsInStock());
	}
}
