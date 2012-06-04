package orm;

import java.sql.SQLException;

public interface DataMapper<K, T extends Entity<K>>{
	Iterable<T> loadAll() throws SQLException;
	T loadById(K id) throws SQLException;
	int update(T value) throws SQLException;
	void insert(T value) throws SQLException;
	void delete(T value) throws SQLException;
}
