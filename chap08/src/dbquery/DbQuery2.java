package dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import spring.Dept;

public class DbQuery2 {
	private JdbcTemplate jdbcTemplate;

	

	public DbQuery2(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Dept> selectDept() {
	    List<Dept> results = jdbcTemplate.query("select * from dept", 
	        (rs, rowNum) -> new Dept(
	            rs.getString("deptno"),
	            rs.getString("dname"),
	            rs.getString("loc")
	        )
	    );
	    return results;
	}
}
