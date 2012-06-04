package orm;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcCmd<T> {
	String getSql() throws SQLException;
	void bind(PreparedStatement stmt, Object...args) throws SQLException;
}
