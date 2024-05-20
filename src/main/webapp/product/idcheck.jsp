<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP 페이지의 속성을 지정하는 지시자입니다. 
    여기서는 페이지의 언어를 Java로, 콘텐츠 유형을 HTML로 설정하고, 
    문자 인코딩을 UTF-8로 지정합니다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- JSTL(Core) 라이브러리를 사용하기 위한 태그 라이브러리 선언 -->
<!-- JSTL(Core) 라이브러리의 주요 기능은 반복문, 조건문, 변수 지원 등이 있습니다. -->
<!DOCTYPE html>
<html>
   <head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>IDcheck</title>
		<!-- 외부 CSS 파일 링크 -->
		<link rel="stylesheet" type="text/css" href="/whisky/css/member.css">
		<!-- 부트스트랩 CSS CDN 링크 -->
		<link rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
		<!-- 외부 JavaScript 파일 링크 -->
		<script src="/whisky/script/member.js"></script>
		<!-- font-awesome 아이콘 CSS CDN 링크 -->
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
			integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
			crossorigin="anonymous" referrerpolicy="no-referrer" />
		<style>
	        body, html {
	            height: 100%;
	        }

	        .center-container {
	            height: 100%;
	            display: flex;
	            justify-content: center;
	            align-items: center;
	        }

	        /* 추가 스타일을 필요로 하는 경우 여기에 추가하세요 */
	    </style>
   </head>
   <body>
   <!-- 아이디 중복확인을 위한 폼이 중앙에 위치하도록 하는 컨테이너 -->
   <div class="center-container" style="background-color:rgba(5, 0, 38, 1);">
      <form action="idCheck.do" method="get" name="frm">
         <div class="d-flex flex-column justify-content-center align-items-center">
            <div style="color:rgba(242, 214, 162, 1);">
               <!-- 제목 -->
               <h2>아이디 중복확인</h2>
            </div>
            <!-- 아이디 중복 확인을 위한 입력 폼 -->
            <div class="input-group-prepend">
               <span class="input-group-text"> 
                  <!-- 사용자 아이콘 -->
                  <i class="fa-solid fa-user"></i>
               </span> 
               <!-- 아이디 입력란 -->
               <input type="text" name="userid" id="userid" value="${userid}"> 
               <!-- 사용자가 입력한 아이디를 표시하는 입력 필드 -->
               <input type="hidden" name="reid" id="reid" value="${userid}">
               <!-- 중복 체크 버튼 -->
               <input type="submit" class="btn custom-button" value="중복체크" onclick="return idCheck2()"><br>
            </div>
            <c:if test="${result==1}">
               <!-- 중복체크결과가 1이면 중복된 아이디임 -->
               <script>
                  opener.document.frm.userid.value=""; // 부모 창의 userid 초기화
               </script>   
               <div style="color:rgba(242, 214, 162, 1);">
                  <!-- 이미 사용 중인 아이디 메시지 -->
                  <strong> ${userid}</strong> 는 이미 사용중인 ID입니다.    
               </div>
            </c:if>
            <c:if test="${result==-1}">
               <!-- 중복체크결과가 -1이면 중복되지않은 아이디임 -->
               <div style="color:rgba(242, 214, 162, 1);">
                  <!-- 사용 가능한 아이디 메시지 -->
                  <strong> ${userid}</strong> 는 사용가능 합니다. 
               </div>
               <!-- 사용 버튼 -->
               <input type="button" class="btn custom-button" value="사용" class="cancel" onclick="idok()">
               <!-- 사용가능한 id임을 알리는 메시지와 사용 버튼 표시, 사용버큰을 틀릭하면 idok('${userid}') 함수가 호출 -->
            </c:if>
         </div>
      </form>
   </div>
   </body>
</html>
