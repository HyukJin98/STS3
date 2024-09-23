package domain.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
public class Address {
	private String city;
	private String street;
	private String zipcode;
	
}
