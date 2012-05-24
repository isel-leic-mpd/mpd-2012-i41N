package orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.google.inject.Inject;

import model.Employee;

public class DbUtil {

	final DataSource ds;
	final String user;
	String password;

	final static String sqlEmployees = "SELECT Title, FirstName, LastName, BirthDate FROM Employees";

	@Inject
	public DbUtil(DataSource ds, @DbUser String user, @DbPasswd String password) {
		this.ds = ds;
		this.user = user;
		this.password = password;
	}

	public Iterable<Employee> loadEmployees() throws SQLException{
		List<Employee> res = new LinkedList<Employee>(); 
		try(
			Connection connect =  ds.getConnection(user, password);
			PreparedStatement stmt = connect.prepareStatement(sqlEmployees);
			ResultSet set = stmt.executeQuery()
		){
			while(set.next()){
				res.add(new Employee(
						set.getString(1), 
						set.getString(2), 
						set.getString(3), 
						set.getDate(4)));
			}
			return res;
		}
	}
	public Employee loadEmployeeById(int id) throws SQLException{
		try(
			Connection connect =  ds.getConnection(user, password);
			PreparedStatement stmt = connect.prepareStatement(sqlEmployees + " WHERE EmployeeId = ?");
		){
			stmt.setInt(1, id);
			ResultSet set = stmt.executeQuery();
			set.next();
			return new Employee(
					set.getString(1), 
					set.getString(2), 
					set.getString(3), 
					set.getDate(4));
		}

	}
}
