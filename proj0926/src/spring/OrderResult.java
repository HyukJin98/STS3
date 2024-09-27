package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderResult {
	private String name;
	private String item;
	private int count;
	private int price;
	private int orderPrice;
	private String orderDate;

}
