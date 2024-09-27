package spring;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ItemDao {
	private JdbcTemplate jdbcTemplate;

	public ItemDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Long insertItem(Item item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO item(name,price,stockquantity) VALUES (?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
            ps.setInt(3, item.getStockquantity());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
	
	public List<Item> selectAllItem() {
	    List<Item> results = jdbcTemplate.query("select * from item", 
	        (rs, rowNum) -> new Item(
	            rs.getLong("id"),
	            rs.getString("name"),
	            rs.getInt("price"),
	            rs.getInt("stockquantity")
	        )
	    );
	    return results;
	}
}
