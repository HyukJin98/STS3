package service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import spring.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberLoginService implements MemberImpl {

    private JdbcTemplate jdbcTemplate;

    public MemberLoginService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 다른 메서드들...

    @Override
    public Member findByNameAndCity(String name, String city) {
        String sql = "SELECT * FROM member WHERE name = ? AND city = ?";
        
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{name, city}, new MemberRowMapper());
        } catch (Exception e) {
            // 예외가 발생하면 null 반환
            return null;
        }
    }

    // RowMapper 구현
    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("city"),
                rs.getString("street"),
                rs.getString("zipcode")
            );
        }
    }
}
