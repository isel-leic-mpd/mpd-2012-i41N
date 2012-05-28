package orm;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public interface JdbcBinder<T> {
	void bind(PreparedStatement stmt, int idx, T arg) throws SQLException;
	
	public static final JdbcBinder<Integer> BindInt = new JdbcBinder<Integer>() {
		public void bind(PreparedStatement stmt, int idx, Integer arg) throws SQLException {
			stmt.setInt(idx, arg);
		}
	};
	public static final JdbcBinder<String> BindString= new JdbcBinder<String>() {
		public void bind(PreparedStatement stmt, int idx, String arg) throws SQLException {
			stmt.setString(idx, arg);
		}
	}; 
	public static final JdbcBinder<Date> BindDate= new JdbcBinder<Date>() {
		public void bind(PreparedStatement stmt, int idx, Date arg) throws SQLException {
			stmt.setDate(idx, new java.sql.Date(arg.getTime()));
		}
	};
}
