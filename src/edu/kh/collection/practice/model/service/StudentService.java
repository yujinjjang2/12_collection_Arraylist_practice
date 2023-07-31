package edu.kh.collection.practice.model.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.collection.practice.model.vo.Student;

public class StudentService {
	
	private Scanner sc = new Scanner(System.in);
	
	private List<Student> studentList = new ArrayList<Student>();
	
	public StudentService() {
		
		studentList.add( new Student("홍길동", 25, "서울시 중구", 'M', 90));
		studentList.add( new Student("고영희", 23, "경기도 안산시", 'F', 100) );
		studentList.add( new Student("강아지", 30, "서울시 강남구", 'M', 80) );
		studentList.add( new Student("오미나", 27, "충북 청주시", 'F', 90) );
		studentList.add( new Student("박주희", 24, "서울시 서대문구", 'F', 70) );
	}
	
	public void displayMenu() {
		
		int menuNum = 0;
		
		do {
			
			System.out.println("\n===========학생 관리 프로그램===========\n");
			System.out.println("1. 학생 정보 추가");
			System.out.println("2. 학생 전체 조회");
			System.out.println("3. 학생 정보 수정");
			System.out.println("4. 학생 정보 제거");
			System.out.println("5. 이름으로 검색(일치)");
			System.out.println("6. 이름으로 검색(포함)");
			
			System.out.println("0. 프로그램 종료");
			
			System.out.print("\n메뉴 번호 선택 >>");
			
			try {
				menuNum = sc.nextInt();
				System.out.println();
				
				switch(menuNum) {
				case 1 : System.out.println( addStudent() ); break;
				case 2 : selectAll(); break;
				case 3 : System.out.println( updateStudent() ); break;
				case 4 : System.out.println( removeStudent() ); break;
				case 5 : searchName1(); break;
				case 6 : searchName2(); break;
				case 0 : System.out.println("프로그램 종료..."); break;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\nerror : 입력형식이 유효하지 않습니다. 다시 시도해주세요.");
				
				sc.nextLine();
				
				menuNum = -1;
			}
			
		} while( menuNum != 0 );
	}
	
	/**
	 * 1. 학생 정보 추가 메서드
	 * - 추가 성공 시 "성공" 실패 시 "실패" 문자열 반환
	 */
	public String addStudent() throws InputMismatchException{
		System.out.println("==========학생 정보 추가==========");
		
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("사는 곳 : ");
		String region = sc.nextLine();
		
		System.out.print("성별(M/F) : ");
		char gender = sc.next().charAt(0);
		
		System.out.print("점수 : ");
		int score = sc.nextInt();
		
		if( studentList.add( new Student(name, age, region, gender, score) ) ) {
			
			return "성공";
		} else {
			
			return "실패";
		}
	}
	
	/**
	 * 2. 학생 전체 조회 메서드
	 */
	public void selectAll() {
		System.out.println("========== 학생 전체 조회 ==========");
		
		if(studentList.isEmpty()) {
			System.out.println("학생 정보가 없습니다.");
			
			return;
		}
		
		int index = 0;
		for( Student std : studentList ) {
			System.out.print( (index++) + "번 : " );
			System.out.println(std.toString());
		}
	}
	
	/**
	 * 3. 학생 정보 수정 메서드
	 */
	public String updateStudent() throws InputMismatchException{
		System.out.println("========== 학생 정보 수정 ==========");
		
		System.out.print("인덱스 번호 입력 : ");
		int index = sc.nextInt();
		
		if(studentList.isEmpty()) {
			return "입력된 학생 정보가 없습니다";
			
		} else if( index < 0 ) {
			return "음수는 입력할 수 없습니다";
			
		} else if( index >= studentList.size() ) {
			return "범위를 넘어선 값을 입력할 수 없습니다.";
			
		} else {
			System.out.println(index + "번째 인덱스에 저장된 학생 정보");
			System.out.println(studentList.get(index));
			
			System.out.print("이름 : ");
			String name = sc.next();
			
			System.out.print("나이 : ");
			int age = sc.nextInt();
			sc.nextLine();
			
			System.out.print("사는 곳 : ");
			String region = sc.nextLine();
			
			System.out.print("성별(M/F) : ");
			char gender = sc.next().charAt(0);
			
			System.out.print("점수 : ");
			int score = sc.nextInt();
			
			Student temp = studentList.set(index, new Student(name, age, region, gender, score));
			
			return temp.getName() + "의 정보가 변경되었습니다.";
			
		}
	}
	
	/**
	 * 4. 학생 정보 제거 메서드
	 */
	public String removeStudent() throws InputMismatchException{
		System.out.println("========== 학생 정보 제거 ==========");
		
		System.out.print("인덱스 번호 입력 : ");
		int index = sc.nextInt();
		
		if(studentList.isEmpty()) {
			return "입력된 학생 정보가 없습니다";
			
		} else if( index < 0 ) {
			return "음수는 입력할 수 없습니다";
			
		} else if( index >= studentList.size() ) {
			return "범위를 넘어선 값을 입력할 수 없습니다.";
			
		} else {
			System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {
				Student temp = studentList.remove(index);
				return temp.getName() + "의 정보가 제거되었습니다.";
				
			} else {
				return "취소";
				
			}
		}
	}
	
	
	/**
	 * 5. 이름이 일치하는 학생을 찾아서 조회하는 메서드
	 */
	public void searchName1() {
		System.out.println("=====학생 검색(이름 일치)=====");
		
		System.out.print("검색할 이름 입력 : ");
		String input = sc.next();
		
		boolean flag = true;
		
		for(Student std : studentList) {
			
			if( input.equals( std.getName() ) ) {
				System.out.println(std.toString());
				
				flag = false;
			}
		}
		
		if(flag) {
			System.out.println("검색 결과가 없습니다.");
		}
	}
	
	
	/**
	 * 6. 이름에 특정 문자열이 포함되는 학생을 찾아서 조회하는 메서드
	 */
	public void searchName2() {
		System.out.println("=====학생 검색(문자열 포함)=====");
		
		System.out.print("이름에 포함되는 문자열 입력 : ");
		String input = sc.next();
		
		boolean flag = true;
		
		for(Student std : studentList) {
			
			if( std.getName().contains(input) ) {
				System.out.println(std);
				
				flag = false;
			}
		}
		
		if(flag) {
			System.out.println("검색 결과가 없습니다.");
		}
	}

}
