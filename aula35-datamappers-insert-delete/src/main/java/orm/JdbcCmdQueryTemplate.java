package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCmdQueryTemplate<T> extends JdbcCmdTemplate<T> implements JdbcCmdQuery<T>{

	final JdbcConverter<T> conv;
	
	public JdbcCmdQueryTemplate(String sql, JdbcConverter<T> conv, JdbcBinder...binders) {
		super(sql, binders);
		this.conv = conv;
	}
	@Override
	public T loadRows(ResultSet rs) throws SQLException {
		return conv.convert(rs);
	}

}
