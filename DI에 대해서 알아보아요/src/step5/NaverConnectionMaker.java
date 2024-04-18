package step5;

import java.sql.Connection;
import java.sql.DriverManager;
//인터페이스 > 중복상속에 대한 제한 사라짐 
public class NaverConnectionMaker implements ConnectionMaker{
	@Override
	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String id = "root";
		String pw = "seok99";
		String JDBC_URL = "jdbc:mysql://localhost:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		Connection con = DriverManager.getConnection(JDBC_URL,id,pw);
		return con;	
	}
}
