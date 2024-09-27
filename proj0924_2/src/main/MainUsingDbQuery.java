package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
import config.DbQueryConfig;
import dbquery.DbQuery;
import domain.entity.Item;
import domain.entity.OrderResult;

public class MainUsingDbQuery {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class,
				DbQueryConfig.class);

		DbQuery dbQuery = ctx.getBean(DbQuery.class);
		int count = dbQuery.count();
		System.out.println(count);
		List<Item> items = dbQuery.findAllItem();
		for (Item item : items) {
			System.out.println(item);
		}
		List<OrderResult> orderResult = dbQuery.orderResult("이혁진");
		for(OrderResult orderResult2 : orderResult) {
			System.out.println(orderResult2);
		}
		ctx.close();
	}
}
