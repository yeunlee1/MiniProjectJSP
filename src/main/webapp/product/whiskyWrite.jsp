<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="css/whiskyWrite.css">
<script src="https://kit.fontawesome.com/6c060c00b1.js"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/6c060c00b1.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="script/whisky.js"></script>

</head>
<body>
<div id="desktop-content">
	<form method="post" name="frm" enctype="multipart/form-data">
			<div id="headpart">
            <a href="whiskyMain.do"> <img src="/whisky/img/logo.png"
               alt="로고이미지" style="position:relative; width:300px; height:auto; z-index:999;" class="mt-2 mb-custom">
            </a>
			</div>
			<div id="btns3" style="justify-content: flex-end; display: flex; float: right; margin-right: 3%; margin-top: -2%;">
   			 <a href="login.do" class="btn btn-sm custom-button" id="btnList">마이페이지</a>
			</div>
		<div id="categorypart">
			<!-- categoripart를 먼저 배치 -->
			
          			  <ul id="categorymenu">
						    <li><a href="whiskyList.do?cno=1">스카치 위스키</a></li>
						    <li><a href="whiskyList.do?cno=2">아이리시 위스키</a></li>
						    <li><a href="whiskyList.do?cno=3">아메리칸 위스키</a></li>
						    <li><a href="whiskyList.do?cno=4">커네이디언 위스키</a></li>
						    <li><a href="whiskyList.do?cno=5">몰트 위스키</a></li>
					</ul>
			
		</div>

		
			<!-- mainview 배치 -->
			
			<div class="container" id="detailwrite" role="main">
				<div id="btns"
					style="justify-content: center; display: flex; float: right;">

					<button type="submit" class="btn btn-sm custom-button" id="btnList"
						onclick="return productCheck">등록</button>

					<a href="javascript:history.back();" class="btn btn-sm custom-button" id="btnList">취소</a>

					<button type="button" class="btn btn-sm custom-button" id="btnList"
						onclick="location.href='whiskyList.do'">목록</button>
				</div>






				<div class="mb-5">

					<!-- &nbsp;&nbsp;(날짜 :=bv.getWriteday().substring(0,10) %>) -->

					<label for="title">제목</label> <input type="text"
						class="form-control" name="title" id="btn"
						placeholder="제목을 입력해 주세요" value="">

				</div>
				<div class="mb-5">
					<label for="reg_id">카테고리</label> <select class="form-control"
						name="cno" id="categorySelect">
						<option value="1">스카치 위스키</option>
						<option value="2">아이리시 위스키</option>
						<option value="3">아메리칸 위스키</option>
						<option value="4">커네이디언 위스키</option>
						<option value="5">몰트 위스키</option>

					</select>
				</div>
				<div class="mb-5">

					<label for="content">내용</label>
					<textarea class="form-control" rows="15" name="bcontent"
						id="content" placeholder="내용을 입력해 주세요"></textarea>
				</div>
				<div>
					<input type="file" name="filepath"><br> * 이미지를 변경할 때만
					선택하세요
				</div>
			</div>
		</form>
	</div>
	<!-- Mobile content--------------------------------------------------------------------------------- -->
 <div id="mobile-content" style="position: relative; min-width:400px;">
   <form method="post" name="frm" enctype="multipart/form-data"> 
    <div style="background-color: #060126; width: 100%; height: 110px; position: absolute;">

         <div style="width: 30%; height: 100%; position: absolute;">
           	<div id="headpart2" style="height: 110px; width:100%;">
		   	     <a href="whiskyMain.do">
		      	    <img src="/whisky/img/logo.png" alt="로고이미지" width="200px" class="mt-2 mb-custom">
			   	</a>
			</div>
          </div>
       </div>
			 <!-- 토글 버튼 -->
				<div style="position: relative;">
				    <div style="width: 15px; height: 50px; position: absolute; left: 90%; transform: translateX(-50%); top: 27px; display: flex; justify-content: center; align-items: center;">
				        <button type="button" data-bs-toggle="collapse" data-bs-target="#demo" style="width: 50px; height: 50px; position: relative; background-color: #060126; border: solid 0px;">
				            <i style="color: white; font-size: 30px;" class="fa-solid fa-bars"></i>
				        </button>
				    </div>
				</div>
			
			<!-- 토글될 내용 -->
					<div id="demo" class="collapse" style="width: 100%; position: relative; top: 110px; background-color: rgb(5, 0, 38);">
					    <ul id="categorymenu-m">
					     	<li><a id="mypage" href="login.do">마이페이지</a></li>
       					    <li><hr></li>
					        <div><li><a href="whiskyList.do?cno=1">스카치 위스키</a></li></div><hr>
					        <div><li><a href="whiskyList.do?cno=2">아이리시 위스키</a></li></div><hr>
					        <div><li><a href="whiskyList.do?cno=3">아메리칸 위스키</a></li></div><hr>
					        <div><li><a href="whiskyList.do?cno=4">커네이디언 위스키</a></li></div><hr>
					        <div><li><a href="whiskyList.do?cno=5">몰트 위스키</a></li></div><hr>
					       
					    </ul>
					</div>
				</div>
			<div style="position:relative;width:90%; left:60px; top:200px;">
		
				<div id="btns" style="position: relative; justify-content: center; display: flex; left:130px;">
				    <button type="submit" class="btn btn-sm custom-button" id="btnList" onclick="return productCheck">등록</button>
				    <a href="javascript:history.back();" class="btn btn-sm custom-button" id="btnList">취소</a>
				    <button type="button" class="btn btn-sm custom-button" id="btnList" onclick="location.href='whiskyList.do'">목록</button>
				</div>
				
					<!-- &nbsp;&nbsp;(날짜 :=bv.getWriteday().substring(0,10) %>) -->
			<div  style="position:relative; width:90%; left:0px; top:-71px; ">	
				<label for="title">제목</label> 
				<input type="text" class="form-control" name="title" id="btn" placeholder="제목을 입력해 주세요" value="">
			</div>
				
				<div class="mb-5" style="position:relative; width:90%; height: 100%; left:0px; top:-65px;">
					<label for="reg_id">카테고리</label> 
					<select class="form-control" name="cno" id="categorySelect">
						<option value="1">스카치 위스키</option>
						<option value="2">아이리시 위스키</option>
						<option value="3">아메리칸 위스키</option>
						<option value="4">커네이디언 위스키</option>
						<option value="5">몰트 위스키</option>

					</select>
				</div>
				<div style="position:relative; width:90%; left:0px; top:-106px;"  >
					<label for="content">내용</label>
					<textarea class="form-control" rows="15" name="bcontent" id="content" placeholder="내용을 입력해 주세요"></textarea>
				</div>
				<div style="position:relative; left:0px; top:-79px;">
					<input type="file" name="filepath"><br> 
					* 이미지를 변경할 때만	선택하세요
				</div>
		
			</div>
	</form>
</div>
</body>
</html>
