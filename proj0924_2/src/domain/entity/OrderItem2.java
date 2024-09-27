package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderItem2 {
	private Long id;
	private Long item_id;
	private Long order_id;
	private Long orderprice;
	private Long count;
}
