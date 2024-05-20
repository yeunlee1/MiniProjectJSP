<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- 빈 파비콘 설정 -->
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보</title>
    <!-- 외부 CSS 파일 링크 -->
    <link rel="stylesheet" type="text/css" href="/whisky/css/member.css">
    <!-- 부트스트랩 CSS CDN 링크 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <!-- 외부 JavaScript 파일 링크 -->
    <script src="/whisky/script/member.js"></script>
    <!-- font-awesome 아이콘 CSS CDN 링크 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" 
    integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" 
    crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div style="height: 80px; background-color: rgba(5, 0, 38, 1);">
<div align="center" class="mt-5 mb-custom">
    <!-- 마이페이지로고 이미지 링크 -->
    <a href="whiskyMain.do">
        <img src="/whisky/img/mypage.png" width="190" height="auto" alt="마이페이지로고" width="500" height="auto">
    </a>
</div>
</div>
<form action="memberUpdate.do" method="post" name="frm">
    <div class="d-flex flex-column justify-content-center align-items-center">
        <div class="mb-3">
            <!-- 수정 로고 이미지 링크 -->
            <a href="login.do"> 
                <img src="/whisky/img/update.png" width="230" height="auto" alt="로고이미지" width="500" height="auto" class="mt-5 mb-custom">
            </a>
            <!-- 입력 폼 -->
            <div class="input-group;,text-left">
                <!-- 아이디 입력란 -->
                <div class="mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> 
                            <!-- 사용자 아이콘 -->
                            <i class="fa-solid fa-user"></i>
                        </span> 
                        <!-- 아이디 입력란 (readonly) -->
                        <input type="text" name="userid" value="${mVo.userid}" readonly>
                    </div>
                </div>      
                <!-- 이름 입력란 -->
                <div class="mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> 
                            <!-- 사용자 아이콘 -->
                            <i class="fa-solid fa-user"></i>
                        </span>
                        <!-- 이름 입력란 -->
                        <input type="text" name="name" value="${mVo.name}" >
                      
                    </div>
                </div>  
                <!-- 비밀번호 입력란 -->
                <div class="mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> 
                            <!-- 자물쇠 아이콘 -->
                            <i class="fa-solid fa-key"></i>
                        </span>
                        <!-- 비밀번호 입력란 -->
                        <input type="password" name="pwd">
                    </div>
                </div>
              <!-- 생년월일 입력란 -->
                <div class="mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> 
                            <!-- 생일 캔들 아이콘 -->
                            <i class="fa-solid fa-cake-candles"></i>
                        </span> 
                        <!-- 생년월일 입력란 (readonly) -->
                        <input type="text" name="birthdate" value="${mVo.birthdate}" readonly>
                      
                    </div>
                </div>
                <!-- 이메일 입력란 -->
                <div class="mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> 
                            <!-- 이메일 아이콘 -->
                            <i class="fa-solid fa-envelope"></i>
                        </span> 
                        <!-- 이메일 입력란 (readonly) -->
                        <input type="text" name="email" value="${mVo.email}" readonly>
                          <!-- 값을 ${mVo.email} 불러옴 -->
                    </div>
                </div>
                <div class="mb-custom2"></div>
                <!-- 수정 및 취소 버튼 -->
                <div align="center">
                    <input type="submit" class="btn custom-button" value="수정" onclick="return joinCheck2()">
                    <input type="reset" class="btn custom-button" value="취소" onclick="return cancel()">
                </div>
                <!-- 메시지 출력 -->
                <div>
                    <!-- 서버로부터 전달된 메시지를 출력하는 JSP 표현식 -->
                    ${message}
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
