package com.whiskywiki.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session=request.getSession(); 
        // 세션 객체를 가져옵니다. 세션이 없으면 새로운 세션을 생성합니다.
        session.invalidate(); 
        // 현재 세션을 무효화합니다. 즉, 세션에 저장된 모든 속성을 제거합니다.
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/login.jsp");
        // 지정된 JSP 페이지로 요청을 전달할 디스패처를 가져옵니다.
        dispatcher.forward(request, response); // 현재 요청과 응답을 지정된 디스패처로 전달합니다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
