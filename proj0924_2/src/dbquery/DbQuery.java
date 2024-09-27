package dbquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import domain.entity.Item;
import domain.entity.Order;
import domain.entity.OrderItem2;
import domain.entity.OrderResult;
import domain.entity.Orders;

public class DbQuery {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Item> findAllItem() {
		String sql = "select * from item";
		return jdbcTemplate.query(sql, (rs, rn) -> {
			Item item = new Item(rs.getLong("id"), rs.getString("name"), rs.getInt("price"),
					rs.getInt("stockquantity"));
			return item;
		});
	}

	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	public List<OrderResult> orderResult(String name) {
		String sql = "select m.name name, i.name item, i.price price, oi.count count, oi.orderprice orderprice, o.order_date order_date\r\n"
				+ "  from member m, orders o, order_item oi, item i\r\n" + " where m.id = o.member_id\r\n"
				+ "   and o.id = oi.order_id\r\n" + "   and oi.item_id = i.id\r\n" + "   and m.name = ?";
		return jdbcTemplate.query(sql, (rs, rn) -> {
			OrderResult orderResult = new OrderResult(rs.getString("name"), rs.getString("item"), rs.getInt("price"),
					rs.getInt("count"), rs.getInt("orderprice"), rs.getString("order_date"));
			return orderResult;
		}, name);
	}

	public Long insertOrder(Orders order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into orders(member_id, order_item_id, city, street, zipcode, order_date)\r\n"
				+ "values (?, ?, ?, ?, ?, now());";

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, order.getMemberId());
			ps.setLong(2, order.getOrderItemId());
			ps.setString(3, order.getCity());
			ps.setString(4, order.getStreet());
			ps.setString(5, order.getZipcode());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}
	
	public Long insertOrderItem(OrderItem2 orderItem) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into order_item(item_id, order_id, orderprice, count)\r\n"
				+ "values (?, ?, ?, ?)";
		
		jdbcTemplate.update(connection ->{
			PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, orderItem.getItem_id());
			ps.setLong(2, orderItem.getOrder_id());
			ps.setLong(3, orderItem.getOrderprice());
			ps.setLong(4, orderItem.getCount());
			return ps;
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	
	
	
	

}
