package step6;
//프로그램의 entry point
public class Main {
	public static void main(String[] args) {
		//필요한 객체를 여기에서 생성한 후 UserDAo에 넣어서 사용.
		//인터페이스 기반으로 객체를 넣어서 사용. 클래스 의존관계X(=클래스 내에 다른 클래스 나오는것)
		//UserDAO가 maker객체를 넣어서 사용함. 객체간의 의존관계가 생김.
		
		NaverConnectionMaker maker = new NaverConnectionMaker();
		
		//사용자 등록하는 코드
		UserDAO dao = new UserDAO(maker); // *** Dependancy Injection ***
		//Annotation으로 객체주입(인자로)이 자동적으로 일어나게 하는게 Spring의 메인기능.
		
		User user = new User("HGD","홍길동","1234");

		try {
			int result = dao.insert(user);
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("사용자 등록 성공");
		} catch (Exception e) {
			System.out.println("사용자 등록 실패");

		}
		
		//사용자 검색하는 코드
		try {
			User user2 = dao.select("HGD");
			System.out.println(user2.getName());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
