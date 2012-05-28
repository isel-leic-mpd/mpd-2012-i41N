package orm.northwind.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.junit.Test;

import junit.framework.Assert;

import model.Employee;

import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcConverter;
import orm.JdbcExecutor;
import orm.northwind.EmployeesLoadAll;
import orm.northwind.EmployeesLoadById;

import app.CfgModule;

import com.google.inject.Guice;

public class TestEmployees {
	final static String sqlEmployees = "SELECT Title, FirstName, LastName, BirthDate FROM Employees";
	final static JdbcConverter<Iterable<Employee>> conv = new JdbcConverter<Iterable<Employee>>(){
		@Override
		public Iterable<Employee> convert(ResultSet rs) throws SQLException{
			LinkedList<Employee> res = new LinkedList<Employee>();
			while(rs.next()){
				res.add(new Employee(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getDate(4)));
			}
			return res;		
		}
	};


	JdbcExecutor db;

	public TestEmployees(){
		db = Guice.createInjector(new CfgModule()).getInstance(JdbcExecutor.class);
	}

	@Test
	public void test_load_all_employees() throws SQLException{
		// JdbcCmd<Iterable<Employee>> loadAll = new EmployeesLoadAll();
		JdbcCmd<Iterable<Employee>> loadAll = new JdbcCmdQuery<Iterable<Employee>>(
				sqlEmployees, 
				conv);
		Iterable<Employee> res = db.executeQuery(loadAll);
		int size = 0;
		for(Employee e:res){size++;}
		Assert.assertEquals(9, size);
	}
	@Test
	public void test_load_byid_employees() throws SQLException{
		JdbcCmd<Iterable<Employee>> loadById = new JdbcCmdQuery<Iterable<Employee>>(
				sqlEmployees + " WHERE EmployeeId = ?", 
				conv,
				JdbcBinder.BindInt);
		Employee e = db.executeQuery(loadById, 7).iterator().next();
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("Sales Representative", e.getTitle());
	}
	@Test
	public void test_load_bytitle_employees() throws SQLException{
		JdbcCmd<Iterable<Employee>> loadByTitle = new JdbcCmdQuery<Iterable<Employee>>(
				sqlEmployees + " WHERE Title = ?", 
				conv,
				JdbcBinder.BindString);
		Iterable<Employee> res = db.executeQuery(loadByTitle, "Sales Representative");
		int size = 0;
		for(Employee e:res){size++;}
		Assert.assertEquals(6, size);		
	}
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
	@Test
	public void test_load_by_title_and_birth_date_employees() throws SQLException, ParseException{
		JdbcCmd<Iterable<Employee>> loadByTitleAndDate = new JdbcCmdQuery<Iterable<Employee>>(
				sqlEmployees + " WHERE Title = ? AND BirthDate > ?", 
				conv,
				JdbcBinder.BindString,
				JdbcBinder.BindDate);
		Iterable<Employee> res = db.executeQuery(loadByTitleAndDate, "Sales Representative", format.parse("1-1-1960"));
		int size = 0;
		for(Employee e:res){size++;}
		Assert.assertEquals(4, size);		
	}
}
