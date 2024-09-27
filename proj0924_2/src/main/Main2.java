package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
import config.DbQueryConfig;
import dbquery.DbQuery;
import domain.entity.Orders;

public class Main2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class,
				DbQueryConfig.class);

		DbQuery dbQuery = ctx.getBean(DbQuery.class);
		Orders order = new Orders(999L, 1L, 0L, "경기 광주", "무슨길", "301", null);
		long result = dbQuery.insertOrder(order);
		System.out.println(result);
		System.out.println("인서트 성공");
	}
}
