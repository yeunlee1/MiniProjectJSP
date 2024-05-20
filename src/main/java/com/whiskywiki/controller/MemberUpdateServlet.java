package com.whiskywiki.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whiskywiki.dao.MemberDAO;
import com.whiskywiki.dto.MemberVO;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 클라이언트로부터 전달된 "userid" 파라미터 값을 가져옴
		String userid=request.getParameter("userid");
        // MemberDAO의 인스턴스 생성
        MemberDAO mDao=MemberDAO.getInstance();
        // 가져온 사용자 아이디를 이용하여 사용자 정보를 MemberVO 객체로 가져옴
        MemberVO mVo=mDao.getMember(userid);
        // 요청 객체에 사용자 정보를 속성으로 설정
        request.setAttribute("mVo", mVo);
        // 요청을 member/memberUpdate.jsp 페이지로 전달하기 위한 RequestDispatcher 생성
		RequestDispatcher dispatcher=request.getRequestDispatcher("product/memberUpdate.jsp");
		 // 요청과 응답 객체를 해당 JSP 페이지로 전달하고 제어를 넘김
		dispatcher.forward(request, response);
		
		System.out.println(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 폼에 입력한 한글이 깨지지 않도록 설정
        request.setCharacterEncoding("UTF-8");
        
        // 사용자가 입력한 데이터들을 받아옴
        String userid=request.getParameter("userid");
        String name=request.getParameter("name");
        String pwd=request.getParameter("pwd");
        String birthdate=request.getParameter("birthdate");
        String email=request.getParameter("email"); // 이메일
        // 사용자가 입력한 데이터들을 MemberVO 객체에 저장
        MemberVO mVo=new MemberVO();
        mVo.setUserid(userid);
        mVo.setName(name);
        mVo.setPwd(pwd); // 비밀번호 설정
        mVo.setBirthdate(birthdate); // 전화번호 설정
        mVo.setEmail(email); // 이메일 설정
        
        
        // MemberDAO의 인스턴스 생성
        MemberDAO mDao=MemberDAO.getInstance();
        
        // 사용자 정보를 업데이트(수정)
        int result = mDao.updateMember(mVo);
        
        if(result > 0) {
        	System.out.println(result);
            // 수정 성공 시 메시지 설정
            request.setAttribute("message", "회원 정보가 성공적으로 수정되었습니다.");
        } else {
            // 수정 실패 시 메시지 설정
            request.setAttribute("message", "회원 정보 수정에 실패하였습니다.");
        }

        // 수정 결과를 보여줄 페이지로 이동
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/login.jsp");
        dispatcher.forward(request, response);
    }

}