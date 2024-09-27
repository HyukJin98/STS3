package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Setter
@ToString
public class Order {
	private Long id;
//	private Member member;
	private Long memberId;
	private String city;
	private String street;
	private String zipcode;
	private String orderDate;
	
//	private List<OrderItem> orderItems = new ArrayList<>();
//	
//	public void addOrderItem(OrderItem orderItem) {
//		orderItems.add(orderItem);
//	}
}
