package orm.northwind.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.inject.Inject;

import orm.JdbcExecutor;
import orm.TemplateDataMapper;
import orm.JdbcBinder;
import orm.JdbcCmdQuery;
import orm.JdbcCmdQueryTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutorMultipleConnection;
import orm.northwind.model.Product;

public class ProductsDataMapper extends TemplateDataMapper<Integer, Product>{
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
	final static JdbcCmdQuery<Iterable<Product>> cmdLoadAll = new JdbcCmdQueryTemplate<Iterable<Product>>(
			sqlProducts , 
			conv);
	
	final static JdbcCmdQuery<Iterable<Product>> cmdLoadById = new JdbcCmdQueryTemplate<Iterable<Product>>(
			sqlProducts + " WHERE ProductId = ?", 
			conv,
			JdbcBinder.BindInt);	
	
	@Inject
	public ProductsDataMapper(JdbcExecutor db) {
		super(db, cmdLoadAll, cmdLoadById, null);
	}

}
