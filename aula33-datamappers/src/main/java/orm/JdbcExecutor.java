package orm;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import orm.northwind.Employee;



import com.google.inject.Inject;


public class JdbcExecutor {

	final DataSource ds;
	final String user;
	String password;

	@Inject
	public JdbcExecutor(DataSource ds, @DbUser String user, @DbPasswd String password) {
		this.ds = ds;
		this.user = user;
		this.password = password;
	}

	public <T> T executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException{ 
		try(
			Connection connect =  ds.getConnection(user, password);
			PreparedStatement stmt = connect.prepareStatement(cmd.getSql());
		){
			cmd.bind(stmt, args);
			ResultSet rs = stmt.executeQuery();
			return cmd.loadRows(rs);
		}
	}
}
