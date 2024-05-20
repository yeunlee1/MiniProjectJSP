package com.whiskywiki.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whiskywiki.dao.WhiskyDAO;
import com.whiskywiki.dto.BoardVO;



/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/whiskyDelete.do")
public class WhiskyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WhiskyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno=request.getParameter("bno");		
		WhiskyDAO wDao=WhiskyDAO.getInstance();
		wDao.deleteProduct(bno);//delete
		System.out.println("삭제 했습니다 글번호 : " + bno);
		response.sendRedirect("whiskyMain.do");//주소변경
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
