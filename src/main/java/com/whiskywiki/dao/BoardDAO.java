package com.whiskywiki.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.whiskywiki.dto.BoardVO;
import com.whiskywiki.dto.LikeVO;

import util.DBManager;

public class BoardDAO {
   //singleton pattern
   private BoardDAO() {}; // private 생성자
   private static BoardDAO instance=new BoardDAO(); // private static instance생성
   public static BoardDAO getInstance() { // 외부에서는 BoardDAO.getInstance() 형식으로 사용
      return instance; // instance 리턴
   }
   
   //목록
   public List<BoardVO> selectAllBoards(){
//      String sql="select * from board order by bno desc";
      String sql = "SELECT board.*, liketable.lno FROM board LEFT JOIN liketable ON board.bno=liketable.bno ORDER BY board.bno DESC";
      List<BoardVO> list=new ArrayList<BoardVO>();
      Connection conn=null;
      Statement stmt=null;
      ResultSet rs=null;
      try {
         conn=DBManager.getConnection();
         stmt=conn.createStatement();
         rs=stmt.executeQuery(sql);
         while(rs.next()) {
            BoardVO bVo=new BoardVO();
            LikeVO lVo=new LikeVO();
            bVo.setBno(rs.getInt("bno"));
            bVo.setTitle(rs.getString("title"));
            bVo.setBcontent(rs.getString("bcontent"));
            bVo.setUserid(rs.getString("userid"));
            bVo.setWritedate(rs.getTimestamp("writedate"));
            bVo.setReadcount(rs.getInt("readcount"));
            bVo.setCno(rs.getInt("cno"));
            bVo.setFilepath(rs.getString("filepath"));            
            bVo.setFilename(rs.getString("filename"));
            bVo.setFiletype(rs.getString("filetype"));
            bVo.setDatasize(rs.getString("datasize"));
            lVo.setLno(rs.getInt("lno"));
            list.add(bVo);
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBManager.close(conn, stmt, rs);
      }
      return list;
   }
   
   //전체글수   
   public int selectCount(Map<String, Object> map) {
	    int totalCount = 0;
	    String sql = "select count(*) from board";

	    // 카테고리 번호가 있으면 해당 카테고리에 맞게 검색
	    if (map.get("cno") != null && (int)map.get("cno") >= 1 && (int)map.get("cno") <= 8) {
	        sql += " where cno = " + map.get("cno") + " ";
	    }

	    if (map.get("searchWord") != null) {
	        sql += (map.get("cno") != null && (int)map.get("cno") >= 1 && (int)map.get("cno") <= 8) ? " and " : " where ";
	        sql += map.get("searchField") + " like '%" + map.get("searchWord") + "%'";
	    }

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBManager.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            totalCount = rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(conn, pstmt, rs);
	    }

	    return totalCount;
	}

// 검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원).
   // searchfield는 검색할 범위 searchword는 검색어 
   public List<BoardVO> selectListPage(Map<String,Object> map) {        
       String sql = "select * "
                + "from ( "
                    + "    select t.*, rownum rnum from ( "
                    + "        select * from board ";

       if (map.get("searchWord") != null)
       {
           sql += " where " + map.get("searchField")
                  + " like '%" + map.get("searchWord") + "%' ";
       } else {
          sql += "where 1=1 ";
       }

       // 카테고리 번호가 있으면 해당 카테고리에 맞게 검색
       if (map.get("cno") != null && (int)map.get("cno") > 0) {
           sql += " and cno = " + map.get("cno") + " ";
       }

       sql += "        order by bno desc "
              + "    ) t "
              + "    where rownum < ?) "
              + "where rnum >= ?";
        
        
        
        List<BoardVO> list = new ArrayList<BoardVO>();
        Connection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
        try {
           conn=DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, map.get("end").toString());
            pstmt.setString(2, map.get("start").toString());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {                
               BoardVO bVo=new BoardVO();
               LikeVO lVo=new LikeVO();
            bVo.setBno(rs.getInt("bno"));
            bVo.setTitle(rs.getString("title"));
            bVo.setBcontent(rs.getString("bcontent"));
            bVo.setUserid(rs.getString("userid"));
            bVo.setWritedate(rs.getTimestamp("writedate"));
            bVo.setReadcount(rs.getInt("readcount"));
            bVo.setCno(rs.getInt("cno"));
            bVo.setFilepath(rs.getString("filepath"));            
            bVo.setFilename(rs.getString("filename"));
            bVo.setFiletype(rs.getString("filetype"));
            bVo.setDatasize(rs.getString("datasize"));
//            lVo.setLno(rs.getInt("lno"));
            list.add(bVo);
            }
        }
        catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }finally {
           DBManager.close(conn, pstmt, rs);
        }
        return list;
    }
   
   
//   //등록
//   public void insertBoard(BoardVO bVo) {
//      String sql="insert into board(num,name,email,pass,title,content)"
//            + " values(board_seq.nextval,?,?,?,?,?)";
//      Connection conn=null;
//      PreparedStatement pstmt=null;
//      try {
//         conn=DBManager.getConnection();//DB연결
//         pstmt=conn.prepareStatement(sql);
//         pstmt.setString(1, bVo.getName());
//         pstmt.setString(2, bVo.getEmail());
//         pstmt.setString(3, bVo.getPass());
//         pstmt.setString(4, bVo.getTitle());
//         pstmt.setString(5, bVo.getContent());
//         pstmt.executeUpdate();
//         
//      }catch(Exception e) {
//         e.printStackTrace();
//      }finally {
//         DBManager.close(conn,pstmt);
//      }
//   }
    
    
  //등록
     public void insertBoard(BoardVO bVo) {
        String sql="insert into board(bno, title, userid, cno, bcontent, filepath, filename) values(board_seq.nextval, ?, ?, ?, ?, ?, ?)";
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
           conn=DBManager.getConnection();
           pstmt=conn.prepareStatement(sql);
           pstmt.setString(1, bVo.getTitle());
           pstmt.setString(2, bVo.getUserid());
           pstmt.setInt(3, bVo.getCno());
           pstmt.setString(4, bVo.getBcontent());
           pstmt.setString(5, bVo.getFilepath());
           pstmt.setString(6, bVo.getFilename());
        
              System.out.println(bVo.getTitle());
              System.out.println(bVo.getUserid());
              System.out.println(bVo.getCno());
              System.out.println(bVo.getBcontent());
              System.out.println(bVo.getFilename());
              System.out.println(bVo.getFilepath());
           
           pstmt.executeUpdate();
        }catch(Exception e) {
           e.printStackTrace();
        }finally {
           DBManager.close(conn, pstmt);
        }
     }
    
    
    
   //조회수증가
   public void updateReadCount(String num) {
      String sql="update board set readcount=readcount+1 where bno=?";
      Connection conn=null;
      PreparedStatement pstmt=null;
      try {
         conn=DBManager.getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, num);
         pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBManager.close(conn, pstmt);
      }
   }
   //글보기
   public BoardVO selectOneBoardByBno(String bno) {
      String sql="select * from board where bno=?";
      BoardVO bVo=null;
      Connection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      try {
         conn=DBManager.getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, bno);
         rs=pstmt.executeQuery();
         if(rs.next()) {
            bVo=new BoardVO();
//            LikeVO lVo=new LikeVO();
            bVo.setBno(rs.getInt("bno"));
            bVo.setTitle(rs.getString("title"));
            bVo.setBcontent(rs.getString("bcontent"));
            bVo.setUserid(rs.getString("userid"));
            bVo.setWritedate(rs.getTimestamp("writedate"));
            bVo.setReadcount(rs.getInt("readcount"));
            bVo.setCno(rs.getInt("cno"));
            bVo.setFilepath(rs.getString("filepath"));            
            bVo.setFilename(rs.getString("filename"));
            bVo.setFiletype(rs.getString("filetype"));
            bVo.setDatasize(rs.getString("datasize"));
//            lVo.setLno(rs.getInt("lno"));
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBManager.close(conn, pstmt, rs);
      }
      return bVo;
   }
// 새로운 메서드 추가
   public String getCategoryName(int cno) {
       String categoryName = ""; // 카테고리 이름을 저장할 변수

       // cno에 해당하는 카테고리 이름을 가져오는 SQL문 작성
       String sql = "SELECT category_name FROM category WHERE cno = ?";

       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       try {
           conn = DBManager.getConnection();
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, cno);
           rs = pstmt.executeQuery();

           if (rs.next()) {
               categoryName = rs.getString("category_name");
           }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           DBManager.close(conn, pstmt, rs);
       }

       return categoryName;
   }
   public BoardVO selectProductByNew(String bno) {
       String sql = "SELECT * FROM board WHERE bno=(SELECT MAX(bno) FROM board)";
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       BoardVO bVo = null;
       try {
           // DB 연결
           conn = DBManager.getConnection();
           // SQL 실행 준비
           pstmt = conn.prepareStatement(sql);
           // SQL 파라미터 설정
//           pstmt.setString(1, bno);
           // SQL 실행 및 결과 가져오기
           rs = pstmt.executeQuery();
           if(rs.next()) {
               // 결과를 BoardVO 객체에 저장
               bVo = new BoardVO();
               bVo.setBno(rs.getInt("bno"));
               bVo.setTitle(rs.getString("title"));
               bVo.setBcontent(rs.getString("bcontent"));
               bVo.setUserid(rs.getString("userid"));
               bVo.setWritedate(rs.getTimestamp("writedate"));
               bVo.setReadcount(rs.getInt("readcount"));
               bVo.setCno(rs.getInt("cno"));
               bVo.setFilepath(rs.getString("filepath"));            
               bVo.setFilename(rs.getString("filename"));
               bVo.setFiletype(rs.getString("filetype"));
               bVo.setDatasize(rs.getString("datasize"));
               

           }
       } catch(Exception e) {
           e.printStackTrace();
       } finally {
           
           // DB 연결 해제
           DBManager.close(conn, pstmt, rs);
       }
       return bVo;
   
   }
   public String selectCategoryName(int cno) {
       String categoryName = null;

       switch (cno) {
           case 1:
               categoryName = "스카치 위스키";
               break;
           case 2:
               categoryName = "아이리시 위스키";
               break;
           case 3:
               categoryName = "아메리칸 위스키";
               break;
           case 4:
               categoryName = "커네이디언 위스키";
               break;
           case 5:
               categoryName = "몰트 위스키";
               break;
           default:
               break;
       }

       return categoryName;
   }

//   //수정
//   public void updateBoard(BoardVO bVo) {
//      String sql="update board set name=?,email=?,pass=?,title=?,content=? "
//            + " where num=?";
//      Connection conn=null;
//      PreparedStatement pstmt=null;
//      try {
//         conn=DBManager.getConnection();//DB연결
//         pstmt=conn.prepareStatement(sql);
//         pstmt.setString(1, bVo.getName());
//         pstmt.setString(2, bVo.getEmail());
//         pstmt.setString(3, bVo.getPass());
//         pstmt.setString(4, bVo.getTitle());
//         pstmt.setString(5, bVo.getContent());
//         pstmt.setInt(6, bVo.getNum());
//         pstmt.executeUpdate();
//      }catch(Exception e) {
//         e.printStackTrace();
//      }finally {
//         DBManager.close(conn, pstmt);
//      }
//   }
//   //삭제
//   public void deleteBoard(String num) {
//      String sql="delete from board where num=?";
//      Connection conn=null;
//      PreparedStatement pstmt=null;
//      try {
//         conn=DBManager.getConnection();
//         pstmt=conn.prepareStatement(sql);
//         pstmt.setString(1, num);
//         pstmt.executeUpdate();
//      }catch(Exception e) {
//         e.printStackTrace();
//      }finally {
//         DBManager.close(conn, pstmt);
//      }
//   }
}