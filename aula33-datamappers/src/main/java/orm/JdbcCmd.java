package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcCmd<T>{
	String getSql() throws SQLException;
	void bind(PreparedStatement stmt, Object...args) throws SQLException;
	T loadRows(ResultSet rs) throws SQLException;
}
