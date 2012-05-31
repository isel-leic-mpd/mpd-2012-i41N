package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcCmdQuery<T> extends JdbcCmd<T>{
	T loadRows(ResultSet rs) throws SQLException;
}
