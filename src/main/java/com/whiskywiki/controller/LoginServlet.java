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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	// doGet 메서드는 클라이언트로부터 GET 요청을 처리합니다.
	// 클라이언트로부터의 요청은 HttpServletRequest 객체를 통해 전달됩니다.
	// 요청에는 클라이언트가 보낸 데이터 및 요청에 대한 다양한 정보가 포함될 수 있습니다.
	// HttpServletResponse 객체는 서버가 클라이언트로부터의 요청에 대해 응답할 때 사용됩니다.
	// 서버의 응답은 이 객체를 통해 설정됩니다.
			throws ServletException, IOException {
		String url="product/login.jsp"; // 기본 URL은 로그인 페이지입니다.
		HttpSession session=request.getSession(); // 현재 요청에 대한 세션 객체를 가져옵니다.
		if(session.getAttribute("loginUser")!=null) { // "loginUser"라는 이름의 속성을 가진 세션이 있는지 확인합니다.
			// 만약 해당 세션 속성이 존재한다면, 사용자는 이미 로그인한 상태입니다.
			url="/product/mypage.jsp"; // 따라서 사용자를 메인 페이지로 이동시킵니다.
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(url); // 요청에 대한 디스패처를 얻습니다.
		dispatcher.forward(request, response); // 요청을 해당 URL로 전달하고, 클라이언트에게 응답합니다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	// 서블릿 클래스에서 클라이언트로부터의 POST 요청을 처리하기 위한 메서드입니다.
			throws ServletException, IOException {
			// 예외명시 //서블릿 예외처리 //입출력 예외처리 
		String url="/product/login.jsp"; // 기본적으로 로그인 페이지로 이동할 URL을 설정합니다.
		String userid=request.getParameter("userid"); // 요청에서 "userid" 파라미터 값을 가져옵니다.
		//jsp에서 인풋 태그에서 value 가져옴
		String pwd=request.getParameter("pwd"); // 요청에서 "pwd" 파라미터 값을 가져옵니다.
		MemberDAO mDao=MemberDAO.getInstance(); // 싱글톤 / MemberDAO의 인스턴스를 생성합니다.
		int result=mDao.userCheck(userid, pwd); // 사용자가 입력한 아이디와 비밀번호를 검증합니다.

		if(result==1) {//pwd가 일치할 때 
			MemberVO mVo=mDao.getMember(userid); // 해당 아이디에 해당하는 회원 정보를 가져옵니다.
			HttpSession session=request.getSession(); // 세션 객체를 생성하거나 기존 세션을 가져옵니다.
			session.setAttribute("loginUser", mVo); // "loginUser"라는 이름으로 회원 정보를 세션에 저장합니다.
			url="/whiskyMain.do"; // 로그인 성공 시 메인 페이지로 이동할 URL을 설정합니다.
		}else if(result==0) {//pwd가 일치하지 않을 때
			request.setAttribute("message", "비밀번호가 맞지 않습니다."); 
			// "message" 속성을 설정하여 비밀번호 불일치 메시지를 저장합니다.			
		}else if(result==-1) {//userid가 없을 때
			request.setAttribute("message", "존재하지 않는 회원입니다."); 
			// "message" 속성을 설정하여 존재하지 않는 회원 메시지를 저장합니다.
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(url); 
		// 지정된 URL로 디스패치(전송)할 디스패처를 가져옵니다. 제어권을 넘김
		dispatcher.forward(request, response);
		// 현재 요청과 응답을 지정된 디스패처로 전달합니다.
		// 결과를 방문객(url)에게 보여준다는 뜻
	}
}
