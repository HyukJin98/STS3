package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
	private Long id;
	private String name;
	private int price;
	private int stockquantity;
}
