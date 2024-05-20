package com.whiskywiki.controller;

import java.io.IOException;

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
 * Servlet implementation class ProductWriteServlet
 */
@WebServlet("/whiskyWrite.do")
public class WhiskyWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WhiskyWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/whiskyWrite.jsp");
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
		
		System.out.println(path);
//		String path="c:\\upload"; //업로드경로
		String encType="UTF-8";
		int sizeLimit=40*1024*1024; // 업로드파일 최대크기.20Mbyte
		//파일업로드
		MultipartRequest multi=new MultipartRequest(request,path,sizeLimit,encType,null);
		
		
		
		String title=multi.getParameter("title");
		String userid = "hyk0910";
		int cno=Integer.parseInt(multi.getParameter("cno"));
		String bcontent=multi.getParameter("bcontent");
		String filepath=multi.getFilesystemName("filepath");
		String fielname=multi.getFilesystemName("filename");
		Integer readcount= 0; 
		BoardVO bVo=new BoardVO();
		bVo.setTitle(title); 
		bVo.setUserid(userid);
		bVo.setCno(cno);
		bVo.setBcontent(bcontent.replace("\n", "<br>"));
		bVo.setFilepath(filepath);
		bVo.setFilename(fielname);
		bVo.setReadcount(readcount);
		System.out.println("writeServlet데이터 등록할때 : "+title);
		System.out.println("writeServlet데이터 등록할때 : "+bcontent);
		System.out.println("writeServlet데이터 등록할때 : "+filepath);
		WhiskyDAO wDao=WhiskyDAO.getInstance();
		wDao.insertBoard(bVo);//insert
		System.out.println("새로운 글이 입력되었습니다. 글번호 : " +bVo.getBno());
		response.sendRedirect("whiskyMain.do"); // 성공 여부에 따라 적절한 처리
	}

}
