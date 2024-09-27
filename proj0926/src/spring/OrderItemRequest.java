package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Setter
@ToString
public class OrderItemRequest {
	private Long id;
	private Long itemId;
	private Long orderId;
	private int orderPrice;
	private int count;

}
