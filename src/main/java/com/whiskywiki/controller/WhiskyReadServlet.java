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
 * Servlet implementation class WhiskyReadServlet
 */
@WebServlet("/whiskyRead.do")
public class WhiskyReadServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WhiskyReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String bno=request.getParameter("bno");
		System.out.println(bno);
		WhiskyDAO wDao=WhiskyDAO.getInstance();
		wDao.updateReadCount(bno); // 조회수증가 
		BoardVO bVo=wDao.selectOneBoardByBno(bno);
		request.setAttribute("whisky", bVo);
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/whiskyRead.jsp");
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