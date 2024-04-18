package step6;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
//중복코드가 너무 많으니 리팩토링 해보자 > 추상으로 구현(재사용성 굿)

//user 테이블에 대한 데이터베이스 처리를 담당하는 클래스 DAO.
public abstract class UserDAO {
	//상속을 안받는 방법 써보자
	private ConnectionMaker connectionMaker; //클래스명 나오면 안좋으니까(클래스간 직접적인 의존관계 생김) 인터페이스로.

	public UserDAO(ConnectionMaker connectionMaker) { 
		this.connectionMaker = connectionMaker;
	}


	// 사용자 입력(추가)메서드 insert
	public int insert(User userId) throws Exception{ // throws : 여기서 try-catch 안쓸거야. 메서드 호출한 곳으로 throw해서 이 안에서 try-catch 안해줘도 된다.
		// 데이터베이스 처리 - 6단계의 database 처리코드 작성
		Connection con = connectionMaker.getConnection();
		String sql = "insert into user valude(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		//물음표를 채우자요
		pstmt.setString(1, userId.getId());
		pstmt.setString(2, userId.getName());
		pstmt.setString(3, userId.getPassword());

		int result = pstmt.executeUpdate();  //result가 1이어야 제대로 수행된것

		pstmt.close();
		con.close();
		
		return result;
	}

	// 사용자 검색 메서드 select
	public User select(String userId) throws Exception{

		Connection con = connectionMaker.getConnection();
		String sql = "select * from user where id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		
		ResultSet rs = pstmt.executeQuery();  //ResultSet 클래스는 멀까?
		
		User user = null;
		if(rs.next()) {
			user = new User(rs.getString("id"),
					rs.getString("name"),
					rs.getString("password"));
		}

		pstmt.close();
		con.close();

		return user;
	}
}
