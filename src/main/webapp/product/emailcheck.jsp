<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL Core태그 라이브러리를 사용하기 위한 선언 -->

<!DOCTYPE html>
<!-- 
    ID 중복체크를 위한 화면
 -->

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
    <meta charset="UTF-8">
    <title>이메일중복체크</title>
    <!-- 외부 JavaScript 파일 링크 -->
    <script src="/whisky/script/member.js"></script>

    <!-- 추가 스타일 -->
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
    </style>
</head>
<body>
    <!-- 이메일 중복확인을 위한 폼이 중앙에 위치하도록 하는 컨테이너 -->
    <div class="center-container" style="background-color: rgba(5, 0, 38, 1);">
        <form action="emailCheck.do" method="get" name="frm">
            <!-- 이메일 중복을 확인하기 위한 폼 생성. emailCheck.do 서블릿으로 GET 방식으로 전송 -->
            <div class="d-flex flex-column justify-content-center align-items-center">
                <div style="color: rgba(242, 214, 162, 1);">
                    <!-- 제목 -->
                    <h2>이메일 중복확인</h2>
                </div>
                <!-- 이메일 입력란과 중복 체크 버튼을 포함한 입력 그룹 -->
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa-solid fa-envelope"></i></span> 
                    <!-- 이메일 입력 필드 -->
                    <input type="text" name="email" value="${email}"> 
                    <!-- 이메일 값 전달을 위한 hidden 필드 -->
                    <input type="hidden" name="reid2" id="reid2" value="${email}"> 
                    <!-- 중복 체크 버튼 -->
                    <input type="submit" class="btn custom-button" value="중복체크" onclick="return emailCheck2()">
                </div>
                <!-- 이메일 중복 결과에 따라 다른 메시지 표시 -->
                <c:if test="${result==1}">
                    <!-- 중복 체크 결과가 1인 경우: 이미 사용 중인 이메일 -->
                    <script>
                        // 부모 창의 입력 필드 초기화
                        opener.document.frm.email.value="";
                    </script>
                    <br>
                    <div style="color: rgba(242, 214, 162, 1);">
                        <!-- 이미 사용 중인 이메일 메시지 -->
                        <strong>${email}</strong>는 이미 사용중인 이메일 입니다.
                    </div>
                </c:if>
                <c:if test="${result==-1}">
                    <!-- 중복 체크 결과가 -1인 경우: 사용 가능한 이메일 -->
                    <br>
                    <div style="color: rgba(242, 214, 162, 1);">
                        <!-- 사용 가능한 이메일 메시지 -->
                        <strong>${email}</strong> 는 사용가능 합니다.
                    </div>
                    <!-- 사용 버튼 -->
                    <input type="button" class="btn custom-button" value="사용" class="cancel" onclick="emailok('${email}')">
                    <!-- 사용 가능한 이메일을 알리는 메시지와 사용 버튼 표시, 사용 버튼을 클릭하면 emailok('${email}') 함수 호출 -->
                </c:if>
            </div>
        </form>
    </div>
</body>
</html>