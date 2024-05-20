<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>join</title>
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
</head>
<body>
<div style="height: 80px; background-color: rgba(5, 0, 38, 1);">>
<!-- 로고 이미지 링크 -->
<a href="/login.do"> 
     <img src="/whisky/img/logo.png" alt="로고이미지" width="190" height="auto" class="mt-2 mb-custom"></a>
</div>
	<form action="join.do" method="post" name="frm">
		<div class="d-flex flex-column justify-content-center align-items-center">
			<div class="mb-custom"></div>
			<!-- 회원가입 로고 이미지 -->
			<a href="join.jsp"> <img src="/whisky/img/joinlogo.png" width="190" height="auto"  alt="로고이미지"
				width="500" height="auto" class="mt-5 mb-custom"></a>
			<!-- 아이디 입력란 -->
			<div class="input-group;,text-left">
				<div class="mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i class="fa-solid fa-user"></i>
						</span> 
						<input type="text" placeholder="아이디" name="userid"> 
						<input type="hidden" name="reid"> 
						<input type="button" class="btn custom-button" value="중복" onclick="idCheck()">
					</div>
				</div>
				<!-- 이름 입력란 -->
				<div class="mb-3">
					<!-- 컨텐츠 사이에 넓이-->
					<div class="input-group-prepend">
						<span class="input-group-text"> <i class="fa-solid fa-user"></i>
						</span> <input type="text" placeholder="이름" name="name">
					</div>
				</div>
				<!-- 비밀번호 입력란 -->
				<div class="mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i class="fa-solid fa-key"></i>
						</span> <input type="password" placeholder="비밀번호" name="pwd">
					</div>
				</div>
				<!-- 생년월일 입력란 -->
				<div class="mb-3">
					<div>
						<div class="input-group-prepend">
							<span class="input-group-text"> <i
								class="fa-solid fa-cake-candles"></i>
							</span> 
							<input type="text" placeholder="생년월일 EX)19900910" name="birthdate"
								>
						</div>
					</div>
				</div>
				<!-- 이메일 입력란 -->
				<div class="mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"> <i
							class="fa-solid fa-envelope"></i>
						</span> 
						<input type="text" placeholder="이메일" name="email">
						<input type="hidden" name="reid2"> 
						<input type="button" class="btn custom-button" value="중복" onclick="emailCheck()">
					</div>
				</div>
				<div class="mb-custom2"></div>
				<!-- 등록 및 취소 버튼 -->
				<div align="center">
					<button type="submit" class="btn custom-button"
						onclick="return joinCheck()" style="margin-right: 50px;">등록</button>
					<button class="btn custom-button"
						onclick="location.href='product/login.do'">취소</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
