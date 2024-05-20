<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<script src="/whisky/script/conditions.js"></script>
<link rel="stylesheet" type="text/css" href="/whisky/css/conditions.css">
<link rel="stylesheet" type="text/css" href="/whisky/css/member.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- 외부 CSS 파일 연결 -->
<title>약관 동의</title>
<!-- 문서 제목 설정 -->
</head>
<body>
	<form action="conditions.do" method="post" id="joinForm">
<div style="height: 80px; background-color: rgba(5, 0, 38, 1);">>
<a href="login.do"> 
     <img src="/whisky/img/logo.png" alt="로고이미지" width="190" height="auto" class="mt-2 mb-custom"></a>
</div>
		<ul class="join_box">
			<li class="checkBox check01">
				<ul class="clearfix">
					<li>이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택), 
프로모션 안내 메일 수신(선택)에 모두동의합니다.</li>
					<li class="checkAllBtn"><input type="checkbox" name="chkAll"
						id="chkAll" class="chkAll"></li>
				</ul>
			</li>
			<li class="checkBox check02">
				<ul class="clearfix">
					<li>이용약관 동의(필수)</li>
					<li class="checkBtn"><input type="checkbox" name="chk"
						class="chk" required></li>
				</ul> <textarea name="" id="" required>
위스키백과 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 
본 약관은 다양한 위스키백과 서비스의 이용과 관련하여 위스키백과 서비스를 제공하는 
위스키백과 주식회사(이하 ‘위스키백과’)와 이를 이용하는 위스키백과 서비스 회원(이하 ‘회원’) 또는 
비회원과의 관계를 설명하며, 아울러 여러분의 위스키백과 서비스 이용에 도움이 
될 수 있는 유익한 정보를 포함하고 있습니다.
   </textarea>
			</li>
			<li class="checkBox check03">
				<ul class="clearfix">
					<li>개인정보 수집 및 이용에 대한 안내(필수)</li>
					<li class="checkBtn"><input type="checkbox" name="chk"
						class="chk" required></li>
				</ul> <textarea name="" id="" required>
위스키백과 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 
본 약관은 다양한 위스키백과 서비스의 이용과 관련하여 위스키백과 서비스를 제공하는 
위스키백과 주식회사(이하 ‘위스키백과’)와 이를 이용하는 위스키백과 서비스 회원(이하 ‘회원’) 
또는 비회원과의 관계를 설명하며, 아울러 여러분의 위스키백과 서비스 이용에 도움이 될 수 있는 
유익한 정보를 포함하고 있습니다.
   </textarea>
			</li>
			<li class="checkBox check03">
				<ul class="clearfix">
					<li>위치정보 이용약관 동의(선택)</li>
					<li class="checkBtn"><input type="checkbox" name="chk"
						class="chk"></li>
				</ul> <textarea name="" id="">
위스키백과 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 
본 약관은 다양한 위스키백과 서비스의 이용과 관련하여 위스키백과 서비스를 제공하는 
위스키백과 주식회사(이하 ‘위스키백과’)와 
이를 이용하는 위스키백과 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 
아울러 여러분의 위스키백과 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
   </textarea>
			</li>
			<li class="checkBox check04">
				<ul class="clearfix">
					<li>이벤트 등 프로모션 알림 메일 수신(선택)</li>
					<li class="checkBtn"><input type="checkbox" name="chk"
						class="chk"></li>
				</ul>
			</li>
		</ul>
		<ul class="footBtwrap clearfix">
			<li><button type="submit" class="btn custom-button"><strong>동의</strong></button>
			<li><button class="btn custom-button"
						onclick="location.href='login.do'"><strong>취소</strong></button></li>

		</ul>
	</form>
</body>
</html>
