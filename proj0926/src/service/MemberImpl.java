package service;

import spring.Member;

public interface MemberImpl {
	Member findByNameAndCity(String name, String city);
}
