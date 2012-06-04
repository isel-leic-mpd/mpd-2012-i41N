package orm;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import orm.northwind.model.Employee;



import com.google.inject.Inject;


public class JdbcExecutorSingleConnection implements JdbcExecutor{

	final DataSource ds;
	final String user;
	String password;
	Connection connect;
	final boolean rollback;

	@Inject
	public JdbcExecutorSingleConnection(DataSource ds, @DbUser String user, @DbPasswd String password, @DbRollback boolean rollback) {
		this.ds = ds;
		this.user = user;
		this.password = password;
		this.rollback = rollback;
		
	}
	private void initConnection() throws SQLException{
		if(connect == null){
			connect = ds.getConnection(user, password);
			connect.setAutoCommit(false);
		}
	}
	public <T> T executeQuery(JdbcCmdQuery<T> cmd, Object...args) throws SQLException{
		initConnection();
		try(
			PreparedStatement stmt = connect.prepareStatement(cmd.getSql());
		){
			cmd.bind(stmt, args);
			ResultSet rs = stmt.executeQuery();
			return cmd.loadRows(rs);
		}
	}
	public <T> int executeUpdate(JdbcCmd<T> cmd, Object...args) throws SQLException{
		initConnection();
		try(
			PreparedStatement stmt = connect.prepareStatement(cmd.getSql());
		){
			cmd.bind(stmt, args);
			return  stmt.executeUpdate();
		}
	}
	public <K, T extends Entity<K>> K executeInsert(JdbcCmdQuery<K> cmd, T arg) throws SQLException{
		initConnection();
		try(
			PreparedStatement stmt = connect.prepareStatement(cmd.getSql(), Statement.RETURN_GENERATED_KEYS);
		){
			cmd.bind(stmt, arg);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return cmd.loadRows(rs);
		}
	}
	@Override
	public void close() throws Exception {
		if(connect != null){
			if(rollback)
				connect.rollback();
			else
				connect.commit();
			connect.close();
		}
	}
}
