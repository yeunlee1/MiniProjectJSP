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
 * 사용자가 입력한 id가 이미 사용 중인지 확인하는 역활
 */
@WebServlet("/emailCheck.do")
public class EmailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email"); // 사용자가 입력한 이메일을 가져옴
		MemberDAO mDao=MemberDAO.getInstance(); 
		int result=mDao.confirmEmail(email);// 중복 확인 결과와 사용자가 입력한 이메일 를 request에 설정
		request.setAttribute("email", email);
		request.setAttribute("result", result); // idcheck.jsp로 포워딩하여 사용자에게 중복 확인 결과 
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/emailcheck.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
