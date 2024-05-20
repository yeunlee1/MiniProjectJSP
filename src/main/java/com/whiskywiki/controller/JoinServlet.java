package com.whiskywiki.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whiskywiki.dao.MemberDAO;
import com.whiskywiki.dto.MemberVO;


/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//회원등록
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/join.jsp");
		dispatcher.forward(request, response);
		//요청값 또는 반응값을 member/join.jsp 보냉 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			// 예외처리
		
		// 한글 깨짐 방지를 위해 요청의 문자 인코딩을 UTF-8로 설정합니다.
		request.setCharacterEncoding("UTF-8");
		
		// 클라이언트가 폼으로부터 입력된 데이터를 가져옵니다.
		String userid=request.getParameter("userid"); // 사용자가 입력한 id을 가져옴
	    String pwd=request.getParameter("pwd"); // 사용자가 입력한 pwd를 가져옴
	    String name=request.getParameter("name"); // 사용자가 입력한 name을 가져옴
	    String birthdate=request.getParameter("birthdate"); // 사용자가 입력한 birthdate을 가져옴
	    String email=request.getParameter("email"); // 사용자가 입력한 email를 가져옴
		//String admin=request.getParameter("admin"); // 관리자 여부를 가져옵니다.
		
		// MemberVO 객체를 생성하고, 가져온 데이터를 설정합니다.
		MemberVO mVo=new MemberVO();
		 mVo.setUserid(userid);
	     mVo.setPwd(pwd);
	     mVo.setName(name);
	     mVo.setBirthdate(birthdate);
	     mVo.setEmail(email);
		//mVo.setAdmin(Integer.parseInt(admin)); // 관리자 여부 설정
		//parseInt 사용자가 입력한 관리자 여부를 문자열로 받아와서 정수로 변환하고 있습니다. 
		
		// MemberDAO 객체를 가져옵니다.
		MemberDAO mDao=MemberDAO.getInstance();
		
		// 회원 정보를 데이터베이스에 추가하고, 영향을 받은 행의 수를 저장합니다.
		int result=mDao.insertMember(mVo);
		
		// 세션을 가져옵니다.
		HttpSession session=request.getSession();
		// 회원 가입 결과에 따라 메시지를 설정합니다.
		if(result==1) {// 회원 가입이 성공했을 때
			session.setAttribute("userid", mVo.getUserid()); // 세션에 사용자 아이디를 저장합니다.
			request.setAttribute("message", "회원 가입에 성공했습니다."); // 파란색 성공 메시지를 설정합니다.
		}else{// 회원 가입이 실패했을 때
			request.setAttribute("message", "회원 가입에 실패했습니다."); // 빨간색 실패 메시지를 설정합니다.
		}
		// 회원 가입 결과를 출력할 페이지로 포워딩합니다.
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/login.jsp");
		 // 회원가입 성공, 실패에 관계없이 로그인 페이지로 포워딩할 준비
		dispatcher.forward(request, response);// 설정한 페이지로 포워딩
	}
}
