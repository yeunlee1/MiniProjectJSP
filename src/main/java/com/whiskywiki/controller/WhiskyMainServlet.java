package com.whiskywiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whiskywiki.dao.BoardDAO;
import com.whiskywiki.dao.WhiskyDAO;
import com.whiskywiki.dto.BoardVO;

import util.BoardPage;

/**
 * Servlet implementation class WhiskyAllMainServlet
 */
@WebServlet("/whiskyMain.do")
public class WhiskyMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WhiskyMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	String url="/product/whiskyAllMain.jsp";
	    
		String bno=request.getParameter("bno");
		System.out.println(bno);
		WhiskyDAO wDao=WhiskyDAO.getInstance();
		BoardDAO bDao=BoardDAO.getInstance();
		BoardVO bVo=wDao.selectProductByNew(bno);
		wDao.updateReadCount(bno); // 조회수증가 
		
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();

        String searchField = request.getParameter("searchField");
        System.out.println(searchField);
        String searchWord = request.getParameter("searchWord");
        System.out.println(searchWord);
        if (searchWord != null) {
            // 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }
        int totalCount = bDao.selectCount(map);  // 게시물 개수
        System.out.println(totalCount);

        /******* 페이지 처리 start ******************************************************/
       
        int pageSize = 6; // 페이지당 글수
        int blockPage = 5; // 목록 아랫쪽  페이지번호 수

        // 현재 페이지 확인
        int pageNum = 1;  // 기본값
        String pageTemp = request.getParameter("pageNum");
        if (pageTemp != null && !pageTemp.equals(""))
            pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정

        // 목록에 출력할 게시물 범위 계산
        int start = (pageNum - 1) * pageSize + 1;  // 첫 게시물 번호
        int end = pageNum * pageSize; // 마지막 게시물 번호
        map.put("start", start);
        map.put("end", end);
        /* 페이지 처리 end */
        
        // 뷰에 전달할 매개변수 추가
        String pagingString="";
        if(searchWord!=null) {//검색하는 경우
           pagingString = BoardPage.pagingStr(totalCount, pageSize,
                 blockPage, pageNum, "/whisky/whiskyMain.do?searchField=" + searchField + "&searchWord=" + searchWord);
        }else {//검색하지 않는 경우
           pagingString = BoardPage.pagingStr(totalCount, pageSize,
                 blockPage, pageNum, "/whisky/whiskyMain.do?searchField=");  // 바로가기 영역 HTML 문자열
        }
        map.put("pagingString", pagingString);
        map.put("totalCount", totalCount);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);

        // 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드        
        request.setAttribute("map", map);
		
		
        List<BoardVO> whiskyList=bDao.selectListPage(map); // 목록조회    
        request.setAttribute("whiskyList", whiskyList); // view에 전달할 데이터
		
		request.setAttribute("whisky", bVo);
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/whiskyMain.jsp");
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
