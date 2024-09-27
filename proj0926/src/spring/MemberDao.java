package spring;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Long insertMember(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO member(name,city,street,zipcode) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getName());
            ps.setString(2, member.getCity());
            ps.setString(3, member.getStreet());
            ps.setString(4, member.getZipcode());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
	
	public List<Member> selectAllMember() {
	    List<Member> results = jdbcTemplate.query("select * from member", 
	        (rs, rowNum) -> new Member(
	            rs.getLong("id"),
	            rs.getString("name"),
	            rs.getString("city"),
	            rs.getString("street"),
	            rs.getString("zipcode")
	        )
	    );
	    return results;
	}
	
	    public List<Member> findByMember(String name) {
		    List<Member> results = jdbcTemplate.query("select * from member where name = ?", new Object[]{name},
		        (rs, rowNum) -> new Member(
		            rs.getLong("id"),
		            rs.getString("name"),
		            rs.getString("city"),
		            rs.getString("street"),
		            rs.getString("zipcode")
		        )
		    );
		    return results;
		    
	}
}
