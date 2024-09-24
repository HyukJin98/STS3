package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
import config.DbQueryConfig;
import dbquery.DbQuery;
import domain.entity.Item;

public class MainUsingDbQuery {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class,
				DbQueryConfig.class);

		DbQuery dbQuery = ctx.getBean(DbQuery.class);
		int count = dbQuery.count();
		System.out.println(count);
		List<Item> items = dbQuery.findAllItem();
		System.out.println(items);
		ctx.close();
	}
}
