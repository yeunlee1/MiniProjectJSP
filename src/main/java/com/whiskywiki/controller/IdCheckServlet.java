package com.whiskywiki.controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whiskywiki.dao.MemberDAO;

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		
		String userid=request.getParameter("userid"); // 사용자가 입력한 아이디를 가져옵니다.
		MemberDAO mDao=MemberDAO.getInstance(); // MemberDAO 객체를 생성합니다.
		// 싱글톤 패턴으로 생성된 인스턴스 공유
		
		int result=mDao.confirmID(userid);// id 중복을 확인합니다.
		request.setAttribute("userid", userid); // "userid" 속성 이름 // userid속성값 아이디를 요청 속성에 설정합니다.
		request.setAttribute("result", result); // 결과를 요청 속성에 설정합니다.
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/idcheck.jsp"); 
		// 지정된 JSP 페이지로 요청을 전달할 디스패처를 가져옵니다.
		dispatcher.forward(request, response); // 현재 요청과 응답을 지정된 디스패처로 전달합니다.
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
