package test;

import java.util.Optional;

public class OptionalTest {
	public static void main(String[] args) {
//		String nStr = null;
//		if(nStr == null) {
//			nStr = "not null!";
//		}
		String nStr = null;
		String oStr = Optional.ofNullable(nStr).orElse("not null");
		System.out.println(oStr);
		
		String value = "Hello Optional!";
		Optional<String> optionalValue = Optional.ofNullable(value);
		
		if(optionalValue.isPresent()) {
			System.out.println("Value is present: " + optionalValue.get());
		}else {
			System.out.println("Value is not present.");
		}
	}
}
