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

import com.whiskywiki.dao.BoardDAO;
import com.whiskywiki.dto.BoardVO;
import com.whiskywiki.dto.LikeVO;

import util.BoardPage;

/**
 * Servlet implementation class WhiskyListServlet
 */
@WebServlet("/whiskyList.do")
public class WhiskyListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WhiskyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String url="/product/whiskyList.jsp";
      BoardDAO bDao=BoardDAO.getInstance();// dao객체생성         
      
       String cnoParam = request.getParameter("cno");
       int cno = (cnoParam != null && !cnoParam.isEmpty()) ? Integer.parseInt(cnoParam) : 0;       
       
       String categoryName = bDao.selectCategoryName(cno);
       request.setAttribute("categoryName", categoryName);
      
      /* 페이징 처리 *****************************************************/
      Map<String, Object> map = new HashMap<String, Object>();

        String searchField = request.getParameter("searchField");
        String searchWord = request.getParameter("searchWord");
        if (searchWord != null) {
            // 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }
        /****************카테 글수마다 목록표시되도록 (선생님) **********************/
        
        if (cnoParam != null) {
            map.put("cno", cno);
            System.out.println("아왜안돼");
        }
        
        /****************카테 글수마다 목록표시되도록 (선생님) end **********************/        
        
        int totalCount = bDao.selectCount(map);  // 게시물 개수

        /******* 페이지 처리 start ******************************************************/
       
        int pageSize = 10; // 페이지당 글수
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
        map.put("cno", cno);
        /* 페이지 처리 end */
        
        // 뷰에 전달할 매개변수 추가
        String pagingString="";
        if(searchWord!=null) {//검색하는 경우
           pagingString = BoardPage.pagingStr(totalCount, pageSize,
                 blockPage, pageNum, "/whisky/whiskyList.do?searchField=" + searchField + "&searchWord=" + searchWord + "&cno=" + cno);
        }else {//검색하지 않는 경우
           pagingString = BoardPage.pagingStr(totalCount, pageSize,
                 blockPage, pageNum, "/whisky/whiskyList.do?searchField=&cno=" + cno);  // 바로가기 영역 HTML 문자열
        }
        map.put("pagingString", pagingString);
        map.put("totalCount", totalCount);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);

        // 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드        
        request.setAttribute("map", map);
        
      /******* 페이징 처리.끝 **********************************************************/
      
        List<BoardVO> whiskyList=bDao.selectListPage(map); // 목록조회    
      request.setAttribute("whiskyList", whiskyList); // view에 전달할 데이터
      RequestDispatcher dispatcher=request.getRequestDispatcher(url);
      dispatcher.forward(request, response); // 목록페이지로 이동.주소변경없음

      
        
        /**좋아요가될까요?***/
//        List<LikeVO> likeList = bDao.selectLikeList(); // 좋아요 정보 조회 (이 부분은 적절한 메서드로 변경해야 함)
//        request.setAttribute("likeList", likeList); // view에 전달할 데이터
        /**좋아요가될까요?***/
    
      
      /***************액션*************************************************************************************/
//      public class ActionFactory {
//         //singleton pattern
//         
//         private static ActionFactory instance=new ActionFactory();
//         private ActionFactory() {
//            super();
//         }
//         public static ActionFactory getInstance() {
//            return instance;
//         };
//         
//         //command별로 action생성해서 리턴
//         public Action getAction(String command) {
//            Action action=null;
//            System.out.println("ActionFactory : "+command);
//            if(command.equals("board_list")) {
//               action=new BoardListAction(); // 목록
//            }else if(command.equals("board_write_form")) {
//               action=new BoardWriteFormAction(); // 등록화면
//            }
//            return action;
//         }
//      }
//      
//      //command저장
//      String command=request.getParameter("command");
//      System.out.println("BoardServlet에서 요청을 받음을 확인 : "+command);
//      ActionFactory af=ActionFactory.getInstance(); //actionfactory객체생성
//      Action action=af.getAction(command);//command별로 action객체생성
//      if(action!=null) {
//         action.execute(request, response);
//      }

      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}