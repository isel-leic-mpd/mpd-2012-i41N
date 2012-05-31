package orm;

import java.sql.SQLException;

public class TemplateDataMapper<K, T> implements DataMapper<K, T>, AutoCloseable{
	final JdbcExecutor db;
	final JdbcCmdQuery<Iterable<T>> cmdLoadAll;
	final JdbcCmdQuery<Iterable<T>> cmdLoadById;
	final JdbcCmd<T> cmdUpdate;
	
	public TemplateDataMapper(
			JdbcExecutor db, 
			JdbcCmdQuery<Iterable<T>> cmdLoadAll, 
			JdbcCmdQuery<Iterable<T>> cmdLoadById,
			JdbcCmd<T> cmdUpdate) {
		this.db = db;
		this.cmdLoadAll = cmdLoadAll;
		this.cmdLoadById = cmdLoadById;
		this.cmdUpdate = cmdUpdate;
	}

	public Iterable<T> loadAll() throws SQLException{
		return db.executeQuery(cmdLoadAll); 
	}
	
	public T loadById(K id) throws SQLException{
		return db.executeQuery(cmdLoadById, id).iterator().next(); 
	}
	
	public int update(T value) throws SQLException{
		return db.executeUpdate(cmdUpdate, value); 
	}

	@Override
	public void close() throws Exception {
		db.close();
	}
}
