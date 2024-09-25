package main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import config.DbConfig;
import dbquery.DbQuery2;
import spring.Dept;


public class Main2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);
		DbQuery2 dbQuery = ctx.getBean(DbQuery2.class);
		List<Dept> deptList = dbQuery.selectDept();
		for (Dept dept : deptList) {
			System.out.println(dept);
		}
		ctx.close();
	}
}
