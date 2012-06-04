package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCmdTemplate<T> implements JdbcCmd<T>{

	final String sql;
	final JdbcBinder [] binders;
	
	public JdbcCmdTemplate(String sql, JdbcBinder...binders) {
		this.sql = sql;
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
}
