package orm.northwind.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import junit.framework.Assert;


import orm.DbPasswd;
import orm.DbRollback;
import orm.DbUser;
import orm.JdbcBinder;
import orm.JdbcCmdQuery;
import orm.JdbcCmdQueryTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutor;
import orm.JdbcExecutorMultipleConnection;
import orm.JdbcExecutorSingleConnection;
import orm.northwind.mappers.EmployeesDataMapper;
import orm.northwind.model.Employee;

import app.CfgModule;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestEmployees {
	
	Injector inj; 
	
	public TestEmployees(){
		inj = Guice.createInjector(
				Modules.override(new CfgModule()).with(new AbstractModule(){
					@Override
					protected void configure() {
						bind(JdbcExecutor.class).to(JdbcExecutorSingleConnection.class);
						bind(boolean.class).annotatedWith(DbRollback.class).toInstance(true);
					}					
				}));
	}
	@Test
	public void test_update() throws Exception{
		try(EmployeesDataMapper empMapper = inj.getInstance(EmployeesDataMapper.class)){
			Employee e = empMapper.loadById(7);
			Assert.assertEquals("King", e.getLastName());
			Assert.assertEquals("Robert", e.getFirstName());
			Assert.assertEquals("Sales Representative", e.getTitle());
			//
			// Update
			//
			e.setFirstName("Jose");
			e.setLastName("Manel");
			e.setTitle("Engenheiro");
			int res = empMapper.update(e);
			//
			// Assert
			//
			e = empMapper.loadById(7);
			Assert.assertEquals("Jose", e.getFirstName());
			Assert.assertEquals("Manel", e.getLastName());
			Assert.assertEquals("Engenheiro", e.getTitle());
		}
	}

	@Test
	public void test_load_all_employees() throws SQLException{
		EmployeesDataMapper empMapper = inj.getInstance(EmployeesDataMapper.class);
		Iterable<Employee> res = empMapper.loadAll();
		int size = 0;
		for(Employee e:res){size++;}
		Assert.assertEquals(9, size);
	}
	@Test
	public void test_load_byid_employees() throws SQLException{
		EmployeesDataMapper empMapper = inj.getInstance(EmployeesDataMapper.class);
		Employee e = empMapper.loadById(7);
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("Sales Representative", e.getTitle());
	}
}
