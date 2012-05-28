package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.inject.Inject;

import orm.AbstractDataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class ProductsDataMapper extends AbstractDataMapper<Integer, Product>{
	final static String sqlProducts = "SELECT ProductName, UnitPrice, UnitsInStock FROM Products";
	
	final static JdbcConverter<Iterable<Product>> conv = new JdbcConverter<Iterable<Product>>(){
		@Override
		public Iterable<Product> convert(ResultSet rs) throws SQLException{
			LinkedList<Product> res = new LinkedList<Product>();
			while(rs.next()){
				res.add(new Product(
						rs.getString(1), 
						rs.getDouble(2), 
						rs.getInt(3)));
			}
			return res;		
		}
	};
	final static JdbcCmd<Iterable<Product>> cmdLoadAll = new JdbcCmdQuery<Iterable<Product>>(
			sqlProducts , 
			conv);
	
	final static JdbcCmd<Iterable<Product>> cmdLoadById = new JdbcCmdQuery<Iterable<Product>>(
			sqlProducts + " WHERE ProductId = ?", 
			conv,
			JdbcBinder.BindInt);	
	
	@Inject
	public ProductsDataMapper(JdbcExecutor db) {
		super(db, cmdLoadAll, cmdLoadById);
	}

}
