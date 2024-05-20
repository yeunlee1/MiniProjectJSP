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
 
public class WhiskyDAO {
    // 싱글톤 패턴을 위한 생성자 및 인스턴스 선언
    private WhiskyDAO() {}
    private static WhiskyDAO instance = new WhiskyDAO();
    public static WhiskyDAO getInstance() {
        return instance;
    }
 
  //목록
  	public List<BoardVO> selectAllBoards(){
//  		String sql="select * from board order by bno desc";
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
          if (map.get("searchWord") != null) {
              sql += " where " + map.get("searchField") + " "
                     + " like '%" + map.get("searchWord") + "%'";
          }
          Connection conn=null;
  		PreparedStatement pstmt=null;
  		ResultSet rs=null;
          try {
          	conn=DBManager.getConnection();
          	pstmt = conn.prepareStatement(sql);
              rs = pstmt.executeQuery();
              if(rs.next()) {
              	totalCount = rs.getInt(1);
              }
          }
          catch (Exception e) {           
              e.printStackTrace();
          }finally {
          	DBManager.close(conn, pstmt, rs);
          }

          return totalCount;
      }

      // 검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원).
      public List<BoardVO> selectListPage(Map<String,Object> map) {        
          String sql = "select * "
          			+ "from ( "
                       + "    select t.*, rownum rnum from ( "
                       + "        select * from board ";

          if (map.get("searchWord") != null)
          {
              sql += " where " + map.get("searchField")
                     + " like '%" + map.get("searchWord") + "%' ";
          }

          sql += "        order by bno desc "
                 + "    ) t "
                 + " 	where rownum < ?) "
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
//  				lVo.setLno(rs.getInt("lno"));
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

      public void insertBoard(BoardVO bVo) {
    	    String sql = "insert into board(bno, title, userid, cno, bcontent, filepath, readcount) values(board_seq.nextval, ?, ?, ?, ?, ?, 0)";
    	    Connection conn = null;
    	    PreparedStatement pstmt = null;
    	    try {
    	        conn = DBManager.getConnection();
    	        pstmt = conn.prepareStatement(sql);
    	        pstmt.setString(1, bVo.getTitle());
    	        pstmt.setString(2, bVo.getUserid());
    	        pstmt.setInt(3, bVo.getCno());
    	        pstmt.setString(4, bVo.getBcontent());
    	        pstmt.setString(5, bVo.getFilepath());
    	        
    	        pstmt.executeUpdate();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    } finally {
    	        DBManager.close(conn, pstmt);
    	    }
    	}
    
    // 제품 상세보기 메서드
    public BoardVO selectProductByBno(String bno) {
        String sql = "select * from board where bno=?";
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
            pstmt.setString(1, bno);
            // SQL 실행 및 결과 가져오기
            rs = pstmt.executeQuery();
            if(rs.next()) {
                // 결과를 BoardVO 객체에 저장
                bVo = new BoardVO();
                bVo.setBno(rs.getInt("bno"));
                bVo.setUserid(rs.getString("userid"));
                bVo.setTitle(rs.getString("title"));
                bVo.setCno(rs.getInt("cno"));
                bVo.setBcontent(rs.getString("bcontent"));
                bVo.setFilepath(rs.getString("filepath"));
                

            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // DB 연결 해제
            DBManager.close(conn, pstmt, rs);
        }
        return bVo;
    }
 
    // 제품 수정 메서드
    public void updateProduct(BoardVO bVo) {
        String sql = "update board set title=?,bcontent=?,filepath=? where bno=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // DB 연결
            conn = DBManager.getConnection();
            // SQL 실행 준비
            pstmt = conn.prepareStatement(sql);
            // SQL 파라미터 설정
            pstmt.setString(1, bVo.getTitle());
            pstmt.setString(2, bVo.getBcontent());
            pstmt.setString(3, bVo.getFilepath());
            pstmt.setInt(4, bVo.getBno());
            
            System.out.println("whiskyDAO : "+ bVo.getFilepath());
            
            // SQL 실행
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // DB 연결 해제
            DBManager.close(conn, pstmt);
        }
    }

    // 제품 삭제 메서드
    public void deleteProduct(String bno) {
        String sql = "delete from board where bno=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // DB 연결
            conn = DBManager.getConnection();
            // SQL 실행 준비
            pstmt = conn.prepareStatement(sql);            
            // SQL 파라미터 설정
            pstmt.setString(1, bno);
            // SQL 실행
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // DB 연결 해제
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
//             LikeVO lVo=new LikeVO();
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
//             lVo.setLno(rs.getInt("lno"));
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
	        // SQL 실행 및 결과 가져오기
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
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
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // DB 연결 해제
	        DBManager.close(conn, pstmt, rs);
	    }
	    return bVo;
	}
    //whiskyMain에서 최신글을 가져오기위한 메서드
    public BoardVO selectLastBno() {
        BoardVO bVo = null; // 타입을 BoardVO로 변경
        String sql = "SELECT bno FROM (SELECT bno FROM board ORDER BY bno DESC) WHERE ROWNUM = 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

        return bVo;
    }

}