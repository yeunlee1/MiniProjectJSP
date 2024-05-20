package com.whiskywiki.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.whiskywiki.dao.WhiskyDAO;
import com.whiskywiki.dto.BoardVO;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/whiskyUpdate.do")
public class WhiskyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WhiskyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno=request.getParameter("bno");
		WhiskyDAO wDao=WhiskyDAO.getInstance();
		BoardVO bVo=wDao.selectProductByBno(bno);
		System.out.println("whiskyUpdateServlet doGet에서 filepath : " + bVo.getFilepath());
		request.setAttribute("whisky", bVo);
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/whiskyUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//폼에 입력한 한글 깨지지 않게
		request.setCharacterEncoding("UTF-8");
		ServletContext context=getServletContext();
		String path=context.getRealPath("img");
//		String path="c:\\upload"; //업로드경로
		String encType="UTF-8";
		int sizeLimit=20*1024*1024; // 업로드파일 최대크기.20Mbyte
		//파일업로드
		MultipartRequest multi=new MultipartRequest(request,path,sizeLimit,encType,new DefaultFileRenamePolicy());
		String bno=multi.getParameter("bno");
		String userid=multi.getParameter("userid");
		String title=multi.getParameter("title");
		String bcontent=multi.getParameter("bcontent");
		//String filepath=multi.getParameter("filepath");
		String filepath = multi.getFilesystemName("filepath");
		
		System.out.println("multi.getParameter(filepath) whiskyUpdate doPost : " + filepath);
		BoardVO bVo=new BoardVO();
		bVo.setBno(Integer.parseInt(bno));
		bVo.setUserid(userid);
		bVo.setTitle(title);
		bVo.setBcontent(bcontent.replace("\n", "<br>"));     
		bVo.setFilepath(filepath);
		
		System.out.println("DAO로 넘어가기전 Servlet filepath : " + bVo.getFilepath());
		WhiskyDAO wDao=WhiskyDAO.getInstance();
		wDao.updateProduct(bVo);//update
		response.sendRedirect("whiskyMain.do");//주소변경
	}

}
 