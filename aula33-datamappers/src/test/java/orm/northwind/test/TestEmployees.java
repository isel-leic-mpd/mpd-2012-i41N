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

import app.CfgModule;

import com.google.inject.Guice;

public class TestEmployees {
	EmployeesDataMapper empMapper;
	
	public TestEmployees(){
		empMapper = Guice.createInjector(new CfgModule()).getInstance(EmployeesDataMapper.class);
	}

	@Test
	public void test_load_all_employees() throws SQLException{
		Iterable<Employee> res = empMapper.loadAll();
		int size = 0;
		for(Employee e:res){size++;}
		Assert.assertEquals(9, size);
	}
	@Test
	public void test_load_byid_employees() throws SQLException{
		Employee e = empMapper.loadById(7);
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("Sales Representative", e.getTitle());
	}
}
