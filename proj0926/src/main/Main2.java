package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Order;
import spring.OrderDao;
import spring.OrderItemRequest;
import spring.OrderResult;

public class Main2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderDao dbQuery = ctx.getBean(OrderDao.class);

		// 주문 생성
		Order order = new Order(999L, 1L, "경기 광주", "무슨길", "301", null);
		Long num = dbQuery.insertOrder(order);
		System.out.println(num + "번 주문이 생성되었습니다.");
		
		// 상품 주문
		OrderItemRequest orderItemRequest = new OrderItemRequest(0L,2L, num, 1400, 2);
		Long oii = dbQuery.insertOrderItemRequest(orderItemRequest);
		
		// 주문 결과
		for (OrderResult orderResult : dbQuery.selectByOrder("이혁진")) {
			System.out.println(orderResult);
		}
		ctx.close();
	}
}
