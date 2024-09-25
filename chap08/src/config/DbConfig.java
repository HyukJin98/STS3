package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dbquery.DbQuery;
import dbquery.DbQuery2;
import spring.MemberDao;

@Configuration
public class DbConfig {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3307/spring5fs?characterEncoding=utf8");
		ds.setUsername("root");
		ds.setPassword("mysql");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		return ds;
	}
	
	@Bean
	public DbQuery dbQuery() {
		return new DbQuery(dataSource());
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public DbQuery2 dbQuery2() {
		return new DbQuery2(dataSource());
	}
}
