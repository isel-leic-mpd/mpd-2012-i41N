package orm;

import java.sql.SQLException;

public class AbstractDataMapper<K, T> implements DataMapper<K, T>{
	final JdbcExecutor db;
	final JdbcCmd<Iterable<T>> cmdLoadAll;
	final JdbcCmd<Iterable<T>> cmdLoadById;
	
	public AbstractDataMapper(
			JdbcExecutor db, 
			JdbcCmd<Iterable<T>> cmdLoadAll, 
			JdbcCmd<Iterable<T>> cmdLoadById) {
		this.db = db;
		this.cmdLoadAll = cmdLoadAll;
		this.cmdLoadById = cmdLoadById;
	}

	public Iterable<T> loadAll() throws SQLException{
		return db.executeQuery(cmdLoadAll); 
	}
	
	public T loadById(K id) throws SQLException{
		return db.executeQuery(cmdLoadById, id).iterator().next(); 
	}
}
