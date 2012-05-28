package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCmdQuery<T> implements JdbcCmd<T>{

	final String sql;
	final JdbcConverter<T> conv;
	final JdbcBinder [] binders;
	
	public JdbcCmdQuery(String sql, JdbcConverter<T> conv, JdbcBinder...binders) {
		this.sql = sql;
		this.conv = conv;
		this.binders = binders;
	}

	@Override
	public String getSql() throws SQLException {
		return sql;
	}

	@Override
	public void bind(PreparedStatement stmt, Object... args) throws SQLException {
		for(int i = 0; i < args.length; i++){
			binders[i].bind(stmt, i+1, args[i]);
		}
	}

	@Override
	public T loadRows(ResultSet rs) throws SQLException {
		return conv.convert(rs);
	}

}
