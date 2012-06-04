package orm.northwind.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.inject.Inject;

import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcExecutor;
import orm.TemplateDataMapper;
import orm.JdbcBinder;
import orm.JdbcCmdQuery;
import orm.JdbcCmdQueryTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutorMultipleConnection;
import orm.northwind.model.Employee;

public class EmployeesDataMapper extends TemplateDataMapper<Integer, Employee>{
	final static String sqlLoadAll = "SELECT EmployeeId, Title, FirstName, LastName, BirthDate FROM Employees";
	final static String sqlUpdate = "UPDATE Employees SET Title = ?, FirstName = ?, LastName = ?, BirthDate = ? WHERE EmployeeId = ?";
	final static String sqlInsert = "INSERT INTO Employees (Title, FirstName, LastName, BirthDate ) VALUES (?, ?, ?, ?)"; 
	final static String sqlDelete = "DELETE FROM Employees WHERE EmployeeId = ?";
			
	final JdbcConverter<Iterable<Employee>> conv = new JdbcConverter<Iterable<Employee>>(){
		@Override
		public Iterable<Employee> convert(ResultSet rs) throws SQLException{
			LinkedList<Employee> res = new LinkedList<Employee>();
			while(rs.next()){
				int id = rs.getInt(1);
				Employee e = getEntity(id);
				if(e == null){
					e = new Employee(id,rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
					addEntity(id, e);
				}
				res.add(e);
			}
			return res;		
		}
	};
	final JdbcCmdQuery<Iterable<Employee>> cmdLoadAll = new JdbcCmdQueryTemplate<Iterable<Employee>>(
			sqlLoadAll, 
			conv);
	
	final JdbcCmdQuery<Iterable<Employee>> cmdLoadById = new JdbcCmdQueryTemplate<Iterable<Employee>>(
			sqlLoadAll + " WHERE EmployeeId = ?", 
			conv,
			JdbcBinder.BindInt);	

	final JdbcCmd<Employee> cmdUpdate = new JdbcCmdTemplate<>(
			sqlUpdate, 
			new JdbcBinder<Employee>(){public void bind(PreparedStatement stmt, int idx, Employee arg) throws SQLException {
					stmt.setString(idx++, arg.getTitle());
					stmt.setString(idx++, arg.getFirstName());
					stmt.setString(idx++, arg.getLastName());
					stmt.setDate(idx++, new java.sql.Date(arg.getBirthDate().getTime()));
					stmt.setInt(idx++, arg.getId());
			}});
	
	final JdbcCmdQuery<Integer> cmdInsert = new JdbcCmdQueryTemplate<Integer>(
			sqlInsert, 
			new JdbcConverter<Integer>() { public Integer convert(ResultSet rs) throws SQLException {
					return rs.getInt(1);
				}
			},
			new JdbcBinder<Employee>(){public void bind(PreparedStatement stmt, int idx, Employee arg) throws SQLException {
				stmt.setString(idx++, arg.getTitle());
				stmt.setString(idx++, arg.getFirstName());
				stmt.setString(idx++, arg.getLastName());
				stmt.setDate(idx++, new java.sql.Date(arg.getBirthDate().getTime()));
			}}
		);	
	
	final static JdbcCmd<Employee> cmdDelete = new JdbcCmdTemplate<>(
			sqlDelete, 
			new JdbcBinder<Employee>(){public void bind(PreparedStatement stmt, int idx, Employee arg) throws SQLException {
					stmt.setInt(idx++, arg.getId());
			}});
	
	@Inject
	public EmployeesDataMapper(JdbcExecutor db) {
		super(db);
	}

	@Override
	protected JdbcCmdQuery<Iterable<Employee>> cmdLoadAll() {
		return cmdLoadAll;
	}

	@Override
	protected JdbcCmdQuery<Iterable<Employee>> cmdLoadById() {
		return cmdLoadById;
	}

	@Override
	protected JdbcCmd<Employee> cmdUpdate() {
		return cmdUpdate;
	}

	@Override
	protected JdbcCmdQuery<Integer> cmdInsert() {
		return cmdInsert;
	}

	@Override
	protected JdbcCmd<Employee> cmdDelete() {
		return cmdDelete;
	}
}
