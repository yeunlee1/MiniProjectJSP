<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP 페이지의 속성을 지정하는 지시자입니다. 
여기서는 페이지의 언어를 Java로, 콘텐츠 유형을 HTML로 설정하고, 
문자 인코딩을 UTF-8로 지정합니다. -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
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
<div style="height: 80px; background-color: rgba(5, 0, 38, 1);">>
<!-- 로고 이미지 링크 -->
<a href="/login.do"> 
     <img src="/whisky/img/logo.png" alt="로고이미지" width="190" height="auto" class="mt-2 mb-custom"></a>
</div>
<!-- 로그인 폼 -->
<form action="login.do" method="post" name="frm">
    <div class="container align-items-center">
        <div id="main" align="center" style="color:rgba(242, 214, 162, 1);">
            <!-- 로고 이미지 -->
            <img src="/whisky/img/loginlogo.png" width="190" height="auto"  alt="로고이미지"
				width="500" height="auto" class="mt-5 mb-custom">
<!---------- 아이디 ------------->
            <!-- 아이디 입력란 -->
            <div class="row justify-content-center" style="margin-top: 20px;">
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <!-- 사용자 아이콘 -->
                                <i class="fa-solid fa-user"></i>
                            </span>
                        </div>
                        <input type="text" class="form-control" placeholder="ID" name="userid">
                    </div>
                </div>
            </div>
<!-------------- 비밀번호 ------------------>
            <!-- 비밀번호 입력란 -->
            <div class="row justify-content-center mb-custom2">
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <!-- 자물쇠 아이콘 -->
                                <i class="fa-solid fa-key"></i>
                            </span>
                        </div>
                        <input type="password" class="form-control" placeholder="Password" name="pwd">
                    </div>
                </div>
            </div>
            <!-- 로그인 및 회원가입 버튼 -->
            <div class="row justify-content-center mb-4">
                <div class="col-md-4">
                    <!-- 로그인 버튼 -->
                    <button type="submit" class="btn custom-button" value="로그인" onclick="return loginCheck()">
                        <div>로그인</div>
                    </button>  
                    <!-- 회원가입 버튼 -->
                    <button type="button" class="btn custom-button" onclick="location.href='conditions.do'">
                        <div>회원가입</div>
                    </button>  
                </div>
            </div>
            <!-- 비밀번호 및 아이디 찾기 링크 -->
            <div class="row justify-content-center mb-4">
                <div class="col-md-8">
                    <a href="/Information inquiry.do"><strong>비밀번호</strong> 또는 <strong>아이디를 </strong>잊어버리셨나요?</a>
                </div>
            </div>
            <!-- 메시지 출력 부분 -->
            <div class="row justify-content-center">
                <div class="col-md-8">${message}</div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
