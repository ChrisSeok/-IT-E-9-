package step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
//중복코드가 너무 많으니 리팩토링 해보자 > 추상으로 구현(재사용성 굿)

//user 테이블에 대한 데이터베이스 처리를 담당하는 클래스 DAO.
public abstract class UserDAO {
	//상속을 안받는 방법 써보자
	private SimpleConnectionMaker simpleConnectionMaker;
	
	public UserDAO() {
		simpleConnectionMaker = new SimpleConnectionMaker();
	}

	//User table에 대한 데이터베이스 처리를 담당하는 class(DAO)
	//method extraction(메소드 추출 - refactoring 기법 중 하나)

	//추상메서드로 만들어서 판매(?)
//	public abstract Connection getConnection() throws Exception; //protected or public으로 잡는다.
	
	
	// 사용자 입력(추가)메서드 insert
	public int insert(User userId) throws Exception{ // throws : 여기서 try-catch 안쓸거야. 메서드 호출한 곳으로 throw해서 이 안에서 try-catch 안해줘도 된다.
		// 데이터베이스 처리 - 6단계의 database 처리코드 작성
		Connection con = getConnection();

		String sql = "insert into user valude(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		//물음표를 채우자요
		pstmt.setString(1, userId.getId());
		pstmt.setString(2, userId.getName());
		pstmt.setString(3, userId.getPassword());

		//DML SUID 중 
		//select - executeQuery
		//UID - executeUpdate
		int result = pstmt.executeUpdate();  //result가 1이어야 제대로 수행된것

		pstmt.close();
		con.close();
		return result;
	}

	// 사용자 검색 메서드 select
	public User select(String userId) throws Exception{

		Connection con = getConnection();

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
