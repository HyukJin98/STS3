package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import domain.entity.Member;
import domain.value.Address;


public class MainForCommand {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("newMember ")) {
				processNewMember(command.split(" "));
				continue;
			} else if (command.startsWith("newOrder ")) {
				processNewOrder(command.split(" "));
				continue;
			} else if (command.startsWith("newDelivery ")) {
				processNewDelivery(command.split(" "));
				continue;
			}
			printHelp();
		}
	}


	private static void processNewMember(String[] arg) {
		if (arg.length != 6) {
			printHelp();
			return;
		}
		Address address = new Address(arg[3], arg[4], arg[5]);
		Member member = new Member(Long.parseLong(arg[1]), arg[2], address, new ArrayList<>());
		System.out.println("Member 등록:" + member);
	}

	private static void processNewDelivery(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		
	}
	
	private static void processNewOrder(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("newMember id name Street City Zip");
		System.out.println("newOrder 이메일 현재비번 변경비번");
		System.out.println("newDelivery 이메일 현재비번 변경비번");
		System.out.println();
	}
}