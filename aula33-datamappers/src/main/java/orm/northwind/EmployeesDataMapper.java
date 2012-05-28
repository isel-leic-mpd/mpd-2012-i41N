package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.inject.Inject;

import orm.AbstractDataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class EmployeesDataMapper extends AbstractDataMapper<Integer, Employee>{
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
	final static JdbcCmd<Iterable<Employee>> cmdLoadAll = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees, 
			conv);
	
	final static JdbcCmd<Iterable<Employee>> cmdLoadById = new JdbcCmdQuery<Iterable<Employee>>(
			sqlEmployees + " WHERE EmployeeId = ?", 
			conv,
			JdbcBinder.BindInt);	
	
	@Inject
	public EmployeesDataMapper(JdbcExecutor db) {
		super(db, cmdLoadAll, cmdLoadById);
	}

}
