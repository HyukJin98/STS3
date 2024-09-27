package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
import config.DbQueryConfig;
import dbquery.DbQuery;
import domain.entity.OrderItem2;
import domain.entity.Orders;

public class Main3 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class,
				DbQueryConfig.class);

		DbQuery dbQuery = ctx.getBean(DbQuery.class);
		OrderItem2 orderItem = new OrderItem2(999L, 2L, 2L, 3500L, 5L);
		Long result = dbQuery.insertOrderItem(orderItem);
		System.out.println(result);
		System.out.println("인서트 성공");
	}
}
