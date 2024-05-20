<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원</title>
		    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/whisky/css/member.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="/whisky/script/member.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" 
    integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" 
    crossorigin="anonymous" referrerpolicy="no-referrer" />
	</head>
	<body>
			<div style="height: 80px; background-color: rgba(5, 0, 38, 1);">
			    <a href="whiskyMain.do">
			        <img src="/whisky/img/logo.png" alt="로고이미지" width="190" height="auto" class="mt-2 mb-custom">
			    </a>
			</div>



		<div align="center" class="mt-5 mb-custom">
	  		<img src="/whisky/img/mypage.png" width="190" height="auto" alt="마이페이지로고"
				width="500" height="auto">
		</div>
		<form action="logout.do">
			<div>
				<div align="center" class="mt-2 mb-custom">
					안녕하세요. <strong>${loginUser.name}(${loginUser.userid})(${loginUser.admin})</strong>님
					<!-- 서버측에서 저장된 데이터 불러오기 -->
				</div>		
		
					<div align="center">
						<input type="submit" class="btn custom-button" value="로그아웃"> 
						<input type="button" class="btn custom-button" value="회원정보변경" 
						onclick="location.href='memberUpdate.do?userid=${loginUser.userid}'">
						<!-- 회원정보 수정페이지로 넘어가고 현재 회원 정보도 넘어감 -->
					</div>
			</div>
		</form>
	</body>
</html>