package com.whiskywiki.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.whiskywiki.dto.MemberVO;

import util.DBManager;

public class MemberDAO {
	// Singleton pattern
	//인스턴스가 오직 하나만 생성되도록 보장하는 패턴입니다. 
	//이는 해당 클래스의 인스턴스를 전역적으로 접근 가능하게 하고, 
	//프로그램 내에서 유일한 인스턴스를 공유하여 사용할 수 있도록 합니다.

	//private로 선언하여 외부에서의 직접적인 인스턴스 생성을 막습니다.
	private MemberDAO() {}  

	//  이 인스턴스는 클래스가 로드될 때 생성되며, 이 시점에서 클래스의 유일한 인스턴스가 됩니다.
	private static MemberDAO instance=new MemberDAO(); 

	// 정적(static) 메서드인 getInstance()를 통해 외부에서 유일한 인스턴스에 접근할 수 있습니다. 
	//이 메서드는 instance를 반환하므로, 
	//다른 클래스나 코드에서는 이 메서드를 호출하여 유일한 인스턴스에 접근할 수 있습니다.
	public static MemberDAO getInstance() {
		return instance;
	}
	// 변수 초기화 하는 이유 값이 있다면 그 값에 덮어씌어도 되지만
	// 그렇게 일 처리하다보면 그전에 저장해 놓은 미쳐 생각지도 못한 값이 실행될 수있으니
	// 안정성을 위해서 필요할 때마다 초기화하고 값을 넣어야 된다.

	// 로그인
	public int userCheck(String userid,String pwd) { //user 체크 메서드
		//String userid,String pwd = 클라이언트 입력값
		int result=-1; // 초기값은 로그인 실패로 설정합니다.

		// 유저가 입력한 아이디의 비밀번호는 찾는다
		// ? = pstmt.setString(1, userid) 에서 1
		String sql="select pwd from member where userid=?";

		//값이 없게 설정
		Connection conn=null; // 연결관리 
		PreparedStatement pstmt=null; // 컴파일 계획sql
		ResultSet rs=null; // 데이터 읽고 쓰기
		try {
			// 데이터베이스 연결을 얻어옵니다.
			conn=DBManager.getConnection();// 데이터 베이스 연결
			pstmt=conn.prepareStatement(sql); // sql에서 컴파일
			pstmt.setString(1, userid); // 1=sql, userid=입력값 /사용자가 입력한 아이디를 설정합니다.
			rs=pstmt.executeQuery(); // 쿼리를 실행하고 결과를 얻습니다.
			// 쿼리는 데이터베이스에서 정보를 가져오거나 조작하기 위해 사용되는 명령어
			// executeQuery 메서드는 SELECT 문을 실행하고 결과 집합을 반환하는 데 사용

			if(rs.next()) { //rs다음 행이 //next() 반복문 느낌 next() 없으면 한 줄만 실행됨
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd)) { 
					// 비밀번호 부분이 비어있지 않고 비밀번호가 일치하면
					result=1; // 로그인 성공을 나타내는 값으로 설정합니다.
				} else { // 비밀번호가 일치하지 않으면
					result=0; // 비밀번호 불일치를 나타내는 값으로 설정합니다.
				}               
			} else { // 결과가 없다면 (아이디가 존재하지 않는다면)
				result=-1; // 아이디가 없음을 나타내는 값으로 설정합니다.
			}
		} catch(Exception e) {
			e.printStackTrace();// 저 위에 세가지 가 안 될때 콘솔창에 이유 써줌
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result; // 로그인 결과를 반환합니다. (1: 로그인 성공, 0: 비밀번호 불일치, -1: 아이디 없음)
	}
	
	// 회원정보
	public MemberVO getMember(String userid) {
		MemberVO mVo=null; // 회원 정보를 담을 객체를 초기화합니다.
		String sql="select * from member where userid=?"; // SQL 쿼리를 준비합니다.
		Connection conn=null; // 데이터베이스 연결을 위한 Connection 객체입니다.
		PreparedStatement pstmt=null; // SQL 쿼리를 실행하기 위한 PreparedStatement 객체입니다.
		ResultSet rs=null; // 쿼리 실행 결과를 저장하는 ResultSet 객체입니다.
		try {
			conn=DBManager.getConnection(); // 데이터베이스와의 연결을 가져옵니다.
			pstmt=conn.prepareStatement(sql); // 준비된 SQL 쿼리를 실행할 PreparedStatement를 생성합니다.
			pstmt.setString(1, userid); // SQL 쿼리의 첫 번째 물음표에 userid 값을 설정합니다.
			rs=pstmt.executeQuery(); // 쿼리를 실행하고 그 결과를 ResultSet에 저장합니다.
			if(rs.next()) { // 만약 결과가 있다면//
				mVo=new MemberVO(); // MemberVO 객체를 생성합니다.
				// ResultSet으로부터 회원 정보를 읽어와 MemberVO 객체에 저장합니다.
				mVo.setUserid(rs.getString("userid")); // ResultSet rs 저장된 이름을 불러와 설정합니다.
				mVo.setPwd(rs.getString("pwd")); //ResultSet rs 저장된 아이디를 설정합니다.
				mVo.setName(rs.getString("name")); //ResultSet rs 저장된 비밀번호를 설정합니다.
				mVo.setBirthdate(rs.getString("birthdate")); //ResultSet 생년월일 저장된 비밀번호를 설정합니다.
				mVo.setEmail(rs.getString("email")); //ResultSet rs 저장된 이메일을 설정합니다.
				mVo.setAdmin(rs.getInt("admin"));
				//mVo.setAdmin(rs.getInt("admin")); // 관리자 여부 설정 (getAdmin() 메서드 사용)
			}
		} catch(Exception e) {
			e.printStackTrace(); // 예외가 발생하면 예외 정보를 출력합니다.
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mVo; // 조회된 회원 정보를 반환합니다.
	}

	// 아이디 중복 체크를 수행하는 메서드.
	public int confirmID(String userid) { // 사용자가 입력한 아이디를 매개변수로 받습.
		int result = -1; // 결과를 저장하는 변수. 초기값은 중복 아이디가 없음으로 설정.
		String sql = "select userid from member where userid=?"; // SQL 쿼리문을 준비.

		Connection conn = null; // 데이터베이스 연결 객체입니다.
		PreparedStatement pstmt = null; // SQL 쿼리를 실행하기 위한 객체입니다.
		ResultSet rs = null; // 쿼리 실행 결과를 저장하는 객체입니다.
		// 초기화 하는이유는 아래에서 새로운 값을 받기위해서
		try {
			// 데이터베이스 연결을 얻어옵니다.
			conn=DBManager.getConnection(); // 데이터베이스와의 연결을 가져옵니다.
			pstmt = conn.prepareStatement(sql); //컴파일 //준비된 SQL 쿼리를 데이터베이스에 보낼 준비를 합니다.
			pstmt.setString(1, userid); // 1이 db, userid 클라이언트 입력값
			rs = pstmt.executeQuery(); // SQL 쿼리를 실행하고 결과를 얻습니다.

			// 조건문에서 ( 1참 : 0거짓, -1특별사항이나 에라)
			//rs.next() : 요소의 다음 요소를 선택합니다
			if (rs.next()) { // userid 결과가 있다면 (아이디가 존재한다면)
				result = 1; //중복 아이디가 있음을 나타내는 값으로 설정합니다.
			} else { // 결과가 없다면 (아이디가 존재하지 않는다면)
				result = -1; // 중복 아이디가 없음을 나타내는 값으로 설정합니다.
			}
		} catch (Exception e) { // 예외가 발생할 경우 처리합니다.
			e.printStackTrace(); // 예외의 발생 원인을 출력합니다.
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		System.out.println(result);// 건모짱!!!
		return result; // 중복 아이디 확인 결과를 반환합니다. (1: 중복 아이디 있음, -1: 중복 아이디 없음)
	}


	public int confirmEmail(String email) {
		int result = -1;

		// 중복 체크
		String sql = "SELECT email FROM member WHERE email=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 이메일 결과가 있다면 (이미 존재하는 이메일)
				result = 1; // 중복 이메일이 있음을 나타내는 값으로 설정
			} else { // 결과가 없다면 (이메일이 중복되지 않음)
				result = -1; // 중복 이메일이 없음을 나타내는 값으로 설정
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	//회원등록
	public int insertMember(MemberVO mVo) { // insertMember 메서드: 새로운 회원을 데이터 베이스에 등록
		int result=-1;
		String sql="insert into member(userid,pwd,name,birthdate,email,admin) values(?,?,?,?,?,1)";
		//어드민 값을 values(?,?,?,?,?,1) 1로 주면 웹에서 가입하는 사람은 1로 받는다.
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getUserid());
			pstmt.setString(2, mVo.getPwd());
			pstmt.setString(3, mVo.getName());
			pstmt.setString(4, mVo.getBirthdate());
			pstmt.setString(5, mVo.getEmail());
			pstmt.setInt(6, mVo.getAdmin());
			result=pstmt.executeUpdate();//영향을 받은 행의 수 리턴.insert하면 1행이 추가되므로 1을 리턴.
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	// 회원정보 수정 메서드
	public int updateMember(MemberVO mVo) {
		int result = -1;
		String sql = "update member set pwd=?, name=?, birthdate=?, email=? where userid=?";


		// 회원 정보를 업데이트하는 SQL 쿼리
		Connection conn = null; // 데이터베이스 연결 객체
		PreparedStatement pstmt = null; // SQL 쿼리를 실행하기 위한 PreparedStatement 객체

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getName());
			pstmt.setString(3, mVo.getBirthdate()); // 전화번호 설정
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getUserid());
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
}
