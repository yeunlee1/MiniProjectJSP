package com.whiskywiki.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/conditions.do")
public class ConditionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConditionsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			// doGet 메서드는 클라이언트로부터 GET 요청을 처리합니다.
			// 클라이언트로부터의 요청은 HttpServletRequest 객체를 통해 전달됩니다.
			// 요청에는 클라이언트가 보낸 데이터 및 요청에 대한 다양한 정보가 포함될 수 있습니다.
			// HttpServletResponse 객체는 서버가 클라이언트로부터의 요청에 대해 응답할 때 사용됩니다.
			// 서버의 응답은 이 객체를 통해 설정됩니다.
			throws ServletException, IOException {
		String url = "product/conditions.jsp"; // 기본 URL은 로그인 페이지입니다.

		RequestDispatcher dispatcher = request.getRequestDispatcher(url); // 요청에 대한 디스패처를 얻습니다.
		dispatcher.forward(request, response); // 요청을 해당 URL로 전달하고, 클라이언트에게 응답합니다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			// 서블릿 클래스에서 클라이언트로부터의 POST 요청을 처리하기 위한 메서드입니다.
			throws ServletException, IOException {
		// 세션에서 "chkAll" 및 "chk" 속성 가져오기

		String chkAllAttribute = request.getParameter("chkAll");
		String[] chkAttribute = request.getParameterValues("chk");
		//배열의 값을 불러들여야 해서request.getParameter를 쓰는거임 
		// 많이 부족하다 응용할 수 있을 정도로 공부를 해...
		System.out.println("chkAll");

	
		 if ("on".equals(chkAttribute[0]) && "on".equals(chkAttribute[1])) {
			 // "chkAll"	 및 "chk" 속성이 모두 세션에 있는 경우 // 추가적인 처리가 필요하다면 여기에 작성
			 System.out.println(chkAllAttribute);  // 이동할 페이지 설정 
			 String targetPage ="/product/join.jsp"; 
			 
			RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage); // 요청에 대한 디스패처를 얻습니다.
			dispatcher.forward(request, response); // 요청을 해당 URL로 전달하고, 클라이언트에게 응답합니다.
		 
		 } else { // "chkAll" 및 "chk" 속성 중 하나라도 세션에 없는 경우 // conditions.jsp로 리다이렉트
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product/conditions.jsp"); // 요청에 대한 디스패처를 얻습니다.
			dispatcher.forward(request, response); // 요청을 해당 URL로 전달하고, 클라이언트에게 응답합니다.
			}
	
	}

}
