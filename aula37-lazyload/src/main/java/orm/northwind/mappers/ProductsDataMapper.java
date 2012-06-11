package orm.northwind.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.inject.Inject;

import orm.JdbcCmd;
import orm.JdbcExecutor;
import orm.TemplateDataMapper;
import orm.JdbcBinder;
import orm.JdbcCmdQuery;
import orm.JdbcCmdQueryTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutorMultipleConnection;
import orm.northwind.model.Employee;
import orm.northwind.model.OrderDetails;
import orm.northwind.model.Product;

public class ProductsDataMapper extends TemplateDataMapper<Integer, Product>{
	OrderDetailsDataMapper ordersMapper;
	
	final static String sqlProducts = "SELECT ProductName, UnitPrice, UnitsInStock, ProductId FROM Products";
	
	final JdbcConverter<Iterable<Product>> conv = new JdbcConverter<Iterable<Product>>(){
		@Override
		public Iterable<Product> convert(ResultSet rs) throws SQLException{
			LinkedList<Product> res = new LinkedList<Product>();
			while(rs.next()){
				final int productId = rs.getInt(4);
				Iterable<OrderDetails> orders = new Iterable<OrderDetails>() {
					@Override
					public Iterator<OrderDetails> iterator() {
						try {
							return ordersMapper.loadByProductId(productId).iterator();
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}
					}
				};
				res.add(new Product(
						rs.getString(1), 
						rs.getDouble(2), 
						rs.getInt(3),
						orders));
			}
			return res;		
		}
	};
	final JdbcCmdQuery<Iterable<Product>> cmdLoadAll = new JdbcCmdQueryTemplate<Iterable<Product>>(
			sqlProducts , 
			conv);
	
	final JdbcCmdQuery<Iterable<Product>> cmdLoadById = new JdbcCmdQueryTemplate<Iterable<Product>>(
			sqlProducts + " WHERE ProductId = ?", 
			conv,
			JdbcBinder.BindInt);	
	
	@Inject
	public ProductsDataMapper(JdbcExecutor db, OrderDetailsDataMapper ordersMapper) {
		super(db);
		this.ordersMapper = ordersMapper;
	}
	@Override
	protected JdbcCmdQuery<Iterable<Product>> cmdLoadAll() {
		return cmdLoadAll;
	}

	@Override
	protected JdbcCmdQuery<Iterable<Product>> cmdLoadById() {
		return cmdLoadById;
	}

	@Override
	protected JdbcCmd<Product> cmdUpdate() {
		return null;
	}

	@Override
	protected JdbcCmdQuery<Integer> cmdInsert() {
		return null;
	}

	@Override
	protected JdbcCmd<Product> cmdDelete() {
		return null;
	}
}
