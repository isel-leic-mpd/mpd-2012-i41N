package orm;

import java.sql.SQLException;
import java.util.Iterator;

public class TemplateDataMapper<K, T extends Entity<K>> implements DataMapper<K, T>, AutoCloseable{
	final JdbcExecutor db;
	final JdbcCmdQuery<Iterable<T>> cmdLoadAll;
	final JdbcCmdQuery<Iterable<T>> cmdLoadById;
	final JdbcCmd<T> cmdUpdate;
	final JdbcCmdQuery<K> cmdInsert;
	final JdbcCmd<T> cmdDelete;
	
	public TemplateDataMapper(
			JdbcExecutor db, 
			JdbcCmdQuery<Iterable<T>> cmdLoadAll, 
			JdbcCmdQuery<Iterable<T>> cmdLoadById,
			JdbcCmd<T> cmdUpdate,
			JdbcCmdQuery<K> cmdInsert,
			JdbcCmd<T> cmdDelete) {
		this.db = db;
		this.cmdLoadAll = cmdLoadAll;
		this.cmdLoadById = cmdLoadById;
		this.cmdUpdate = cmdUpdate;
		this.cmdInsert = cmdInsert;
		this.cmdDelete = cmdDelete;
	}

	public Iterable<T> loadAll() throws SQLException{
		return db.executeQuery(cmdLoadAll); 
	}
	
	public T loadById(K id) throws SQLException{
		Iterator<T> iter = db.executeQuery(cmdLoadById, id).iterator();
		return iter.hasNext()? iter.next(): null;
	}
	
	public int update(T value) throws SQLException{
		return db.executeUpdate(cmdUpdate, value); 
	}

	@Override
	public void close() throws Exception {
		db.close();
	}

	@Override
	public void insert(T value) throws SQLException {
		K id = db.executeInsert(cmdInsert, value);
		value.setId(id);
	}

	@Override
	public void delete(T value) throws SQLException {
		db.executeUpdate(cmdDelete, value);		
	}
}
