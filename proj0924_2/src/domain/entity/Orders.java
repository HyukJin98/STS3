package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Orders {
	private Long id;
//	private Member member;
	private Long memberId;
	private Long orderItemId;
	private String city;
	private String street;
	private String zipcode;
	private String orderDate;
}
