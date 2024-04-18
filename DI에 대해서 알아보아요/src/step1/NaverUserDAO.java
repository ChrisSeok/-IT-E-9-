package step1;

import java.sql.Connection;
import java.sql.DriverManager;

public class NaverUserDAO extends UserDAO{ //네이버가 UserDAO를 사왔으니 상속받아 구현한다.
	//동적바인딩으로 오버라이딩 된 메서드가 호출됨
	@Override
	public Connection getConnection() throws Exception { //추상클래스 구현
		Class.forName("com.mysql.cj.jdbc.Driver");
		String id = "root";
		String pw = "seok99";
		String JDBC_URL = "jdbc:mysql://localhost:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		Connection con = DriverManager.getConnection(JDBC_URL,id,pw);
		return con;
	}
}
