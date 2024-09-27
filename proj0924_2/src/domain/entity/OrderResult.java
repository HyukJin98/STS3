package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderResult {
	private String name;
	private String item;
	private int price;
	private int count;
	private int orderprice;
	private String order_date;
}
