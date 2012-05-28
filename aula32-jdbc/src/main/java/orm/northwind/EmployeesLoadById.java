package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.Employee;
import orm.JdbcCmd;

public class EmployeesLoadById implements JdbcCmd<Iterable<Employee>>{

	final static String sqlEmployees = 
			"SELECT Title, FirstName, LastName, BirthDate " +
			"FROM Employees " +
			"WHERE EmployeeId = ?";
	
	@Override
	public String getSql() throws SQLException {
		return sqlEmployees;
	}

	@Override
	public void bind(PreparedStatement stmt, Object... args) throws SQLException {
		stmt.setInt(1, (Integer) args[0]);
	}

	@Override
	public Iterable<Employee> loadRows(ResultSet rs) throws SQLException {
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

}
