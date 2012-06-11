package orm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class TemplateDataMapper<K, T extends Entity<K>> implements DataMapper<K, T>, AutoCloseable{
	final protected JdbcExecutor db;
	final Map<K, T> identityMap = new HashMap<K, T>();
	
	
	abstract protected JdbcCmdQuery<Iterable<T>> cmdLoadAll();
	abstract protected JdbcCmdQuery<Iterable<T>> cmdLoadById();
	abstract protected JdbcCmd<T> cmdUpdate();
	abstract protected JdbcCmdQuery<K> cmdInsert();
	abstract protected JdbcCmd<T> cmdDelete();
	
	public TemplateDataMapper(JdbcExecutor db) {
		this.db = db;
	}

	protected T getEntity(K id){
		return identityMap.get(id);
	} 
	protected void addEntity(K id, T e){
		identityMap.put(id, e);
	}
	
	public Iterable<T> loadAll() throws SQLException{
		return db.executeQuery(cmdLoadAll()); 
	}
	
	public T loadById(K id) throws SQLException{
		T value = identityMap.get(id);
		if(value != null) return value;
		Iterator<T> iter = db.executeQuery(cmdLoadById(), id).iterator();
		return iter.hasNext()? iter.next(): null;
	}
	
	public int update(T value) throws SQLException{
		return db.executeUpdate(cmdUpdate(), value); 
	}

	@Override
	public void close() throws Exception {
		db.close();
	}

	@Override
	public void insert(T value) throws SQLException {
		K id = db.executeInsert(cmdInsert(), value);
		identityMap.put(id, value);
		value.setId(id);
	}

	@Override
	public void delete(T value) throws SQLException {
		db.executeUpdate(cmdDelete(), value);
		identityMap.remove(value.getId());
	}
}
