package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import service.MemberLoginService;
import spring.Item;
import spring.ItemDao;
import spring.Member;
import spring.MemberDao;
import spring.Order;
import spring.OrderDao;
import spring.OrderItemRequest;
import spring.OrderResult;

public class MainForSpring {

	private static ApplicationContext ctx = null;
	private static MemberDao memberDao;
	private static ItemDao itemDao;
	private static OrderDao orderDao;
	private static Member loggedInMember;
	private static MemberLoginService memberImpl;
	 
	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("newOrder 멤버아이디 도시 길 집코드");
		System.out.println("newItem 이름 가격 개수");
		System.out.println("newOrderItem 상품아이디 주문아이디 주문가격 갯수");
		System.out.println("memberList");
		System.out.println("itemList");
		System.out.println("orderItemList");
		System.out.println("orderList");
		System.out.println("orList 멤버이름");
		System.out.println("cancleOrder 주문아이디");
		System.out.println("cancleOrderItem 상품아이디");
		System.out.println("logout");
		System.out.println();
	}
	


	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		itemDao = ctx.getBean(ItemDao.class);
		memberDao = ctx.getBean(MemberDao.class);
		orderDao = ctx.getBean(OrderDao.class);
		memberImpl = ctx.getBean(MemberLoginService.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			loggedInMember = null; 
			
		  while (true) {
	            System.out.println("로그인 하세요: ");
	            System.out.println("로그인 명령어: login  이름  도시(공백 두개)");
	            System.out.println("회원가입 명령어: signup 이름 도시 길 집코드");
	            String loginCommand = reader.readLine();
	            if (loginCommand.equalsIgnoreCase("exit")) {
	                System.out.println("종료합니다");
	                System.exit(0);
	            }
	            if(loginCommand.startsWith("signup ")) {
	            	processAddMember(loginCommand.split(" "));
	            	System.out.println("회원가입 성공!!");
	            	continue;
	            }

	            if (loginCommand.startsWith("login ")) {
	                processLogin(loginCommand.split("  "));
	                if (loggedInMember != null) {
	                    break; // 로그인 성공 시 루프 종료
	                }
	            } else {
	                System.out.println("로그인 명령어 사용법: login  이름  도시(공백 두개)");
	            }
	        }
		
		while (true) {
			
		
			System.out.println("명령어를 입력하세요");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다");
				System.exit(0);
			}
			if (command.startsWith("newItem ")) {
				processAddItem(command.split(" "));
				continue;
			} else if (command.startsWith("itemList")) {
				processPrintAllItem();
				continue;
			} else if (command.startsWith("memberList")) {
				processPrintAllMember();
				continue;
			} else if (command.startsWith("orderItemList")) {
				processPrintAllOrderItem();
				continue;
			} else if (command.startsWith("orderList")) {
				processPrintAllOrder();
				continue;
			} else if (command.startsWith("newOrder ")) {
				processAddOrder(command.split(" "));
				continue;
			} else if (command.startsWith("newOrderItem ")) {
				processAddOrderItem(command.split(" "));
				continue;
			} else if (command.startsWith("orList ")) {
				processPrintAllOi(command.split(" "));
				continue;
			} else if (command.startsWith("cancleOrder ")) {
				processCancleOrder(command.split(" "));
				continue;
			} else if (command.startsWith("cancleOrderItem ")) {
				processCancleOrderItem(command.split(" "));
				continue;
			} else if(command.startsWith("logout")) {
				System.out.println("로그아웃 되었습니다.");
		        break; // 내부 루프를 나가고 로그인 루프로 돌아감
			}
			printHelp();
			
		}
		
	
	}
	}

	public static void processAddMember(String[] arg) { // 멤버 등록
		memberDao.insertMember(new Member(0L, arg[1], arg[2], arg[3], arg[4]));
	}

	public static void processPrintAllMember() {
		memberDao.selectAllMember().forEach(m -> System.out.println(m));
		System.out.println("멤버 조회 완료");
	}

	public static void processAddItem(String[] arg) { // 상품 추가
		itemDao.insertItem(new Item(0L, arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3])));
		System.out.println("상품 추가 완료!");
	}

	public static void processPrintAllItem() {
		itemDao.selectAllItem().forEach(i -> System.out.println(i));
		System.out.println("상품 조회 완료");
	}

	public static void processAddOrder(String[] arg) {
		orderDao.insertOrder(new Order(0L, Long.parseLong(arg[1]), arg[2], arg[3], arg[4], null));
		System.out.println("주문 생성 완료!");
	}

	public static void processAddOrderItem(String[] arg) {
		orderDao.insertOrderItemRequest(new OrderItemRequest(0L,Long.parseLong(arg[1]), Long.parseLong(arg[2]),
				Integer.parseInt(arg[3]), Integer.parseInt(arg[4])));
		System.out.println("상품 주문 완료!");
	}

	public static void processPrintAllOi(String[] arg) {
		orderDao.selectByOrder(arg[1]).forEach(a -> System.out.println(a));
		System.out.println("멤버 상품 주문 조회 완료");
	}

	public static void processCancleOrder(String[] arg) {
		if(orderDao.countOrder().getCount() == 0) {
			System.out.println("취소할 상품이 없습니다");
		}else {
		orderDao.cancelOrder(Long.parseLong(arg[1]));
		System.out.println("주문 취소 완료");
		}
	}

	public static void processCancleOrderItem(String[] arg) {
		if(orderDao.countOrderItem().getCount() == 0) {
			System.out.println("취소할 상품이 없습니다");
		}else {
		orderDao.cancelOrderItem(Long.parseLong(arg[1]));
		System.out.println("상품 취소완료");
		}
	}

	public static void processPrintAllOrderItem() {
		orderDao.selectByOrderItem().forEach(b -> System.out.println(b));
		System.out.println("상품 주문 조회 완료");
	}
	
	public static void processPrintAllOrder() {
		orderDao.selectAllOrder().forEach(c -> System.out.println(c));
		System.out.println("주문 조회 완료");
	}
	
	  public static void processLogin(String[] arg) {
	        if (arg.length != 3) {
	            System.out.println("사용법: login 이름 도시");
	            return;
	        }

	        String name = arg[1];
	        String city = arg[2];
	        loggedInMember = memberImpl.findByNameAndCity(name,city);
	        if (loggedInMember != null) {
	            System.out.println("로그인 성공! 환영합니다, " + loggedInMember.getName() + "님.");
	        } else {
	            System.out.println("로그인 실패: 이름 또는 도시가 올바르지 않습니다.");
	        }
	    }
}
	
	
