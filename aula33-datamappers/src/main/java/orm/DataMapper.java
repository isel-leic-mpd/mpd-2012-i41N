package orm;

import java.sql.SQLException;

public interface DataMapper<K, T>{
	Iterable<T> loadAll() throws SQLException;
	T loadById(K id) throws SQLException;
}
