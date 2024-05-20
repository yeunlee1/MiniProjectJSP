<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="css/whiskyList.css">

<script src="https://kit.fontawesome.com/6c060c00b1.js"
   crossorigin="anonymous"></script>
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
   crossorigin="anonymous">
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
   crossorigin="anonymous"></script>
</head>
<body>
   <!-- 데스크탑크기일때 표기 -->
   <div id="desktop-content">

      <form method="post" action="WhiskyDeleteServlet" name="frm"
         enctype="multipart/form-data">

         <div id="headpart">

            <a href="whiskyMain.do"> <img src="/whisky/img/logo.png"
               alt="로고이미지" style="position:relative; width:300px; height:auto; z-index:999;" class="mt-2 mb-custom">
            </a>

            <div id="btns1">
               <a href="login.do" class="btn btn-sm custom-button" id="btnList" style="position:absolute; left:0px; top:120px; width: 100px;">마이페이지</a>
               
            </div>
         </div>
         <div id="categorypart">

            <ul id="categorymenu">
               <li><a href="whiskyList.do?cno=1">스카치 위스키</a></li>
               <li><a href="whiskyList.do?cno=2">아이리시 위스키</a></li>
               <li><a href="whiskyList.do?cno=3">아메리칸 위스키</a></li>
               <li><a href="whiskyList.do?cno=4">커네이디언 위스키</a></li>
               <li><a href="whiskyList.do?cno=5">몰트 위스키</a></li>
            </ul>

         </div>	
            <!-- 메인뷰  --> 
		<div class="pagingdiv" align="center">
  		    <c:choose>
         	<c:when test="${loginUser.userid=='hyk0910'}">
                <div style="text-align: left;">
                    <div id="btns2" style="justify-content: center; display: flex; float: right;left:1200px;top:0px;width:0px; z-index: 999;">
                        <a href="whiskyWrite.do?" class="btn btn-sm custom-button" id="btnList">글쓰기</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:if test="${loginUser.userid!='hyk0910'}">
                </c:if>
            </c:otherwise>
         </c:choose>   
			<h1 id=categorytitle>${categoryName}게시판</h1>
			<div class="row">
				<div class="col-2">번호</div>
				<!-- bno -->
				<div class="col-6">제목</div>
				<!-- title -->
				<div class="col-2">작성일</div>
				<!-- writedate -->
				<div class="col-2">조회수</div>
				<!-- readcount -->
			</div>
			<c:forEach var="whiskyList" items="${whiskyList}">
				<div class="record">
					<div class="col-2">${whiskyList.bno}</div>
					<div class="col-6">
						<a href="whiskyRead.do?bno=${whiskyList.bno}">${whiskyList.title}</a>
					</div>
					<div class="col-2">
						<fmt:formatDate value="${whiskyList.writedate}"
							pattern="yy-MM-dd HH:mm:ss" />
					</div>
					<div class="col-2">${whiskyList.readcount}</div>
				</div>
			</c:forEach>
			<!--  paging ------------------------------------------------------------------------------>
			<form method="get">
				<div class="paging" align="center">${map.pagingString}</div>
			</form>
			<!--  paging.end -->

			<!-- 검색 폼 ------------------------------------------------->
			<form method="get">
				<div class="searchbar" border="1" width="90%">
					<div>
						<div align="center">
							<select name="searchField">
								<option value="title">제목</option>
								<option value="bcontent">내용</option>
							</select> <input type="text" name="searchWord" /> 
							          <input type="submit" class="btn btn-sm custom-button" id="btnList" value="검색" style="position:absolute; left:784px; top:-1px;width:56px;" />
							          <input type="hidden" name="cno" value="<%= request.getParameter("cno") %>" /> <!-- Hidden field 추가 -->
						</div>
					</div>
				</div>
			</form>
		</div>
		</form>
	</div>
	
	<!-- 데스크탑크기일때 표기 끝 -->
	
	<!-- 모바일크기일때 표기-->
	
<div id="mobile-content" style="position: relative;">
<form method="post" action="WhiskyDeleteServlet" name="frm" enctype="multipart/form-data">
<div style="background-color: #060126; width: 100%; height: 110px; position: absolute;">
<div style="width: 30%; height: 100%; position: absolute;">
    <div id="headpart" style="height: 110px; with: 100%;">
        <a href="whiskyMain.do">
            <img src="/whisky/img/logo.png" alt="로고이미지" width="300px" height="70%" class="mt-2 mb-custom">
        </a>
        
    </div>
    </div>
    <!-- 토글 버튼 -->
    <div style="width: 15%; height: 50%; position: absolute; right: 20px; top: 25%; display: flex; justify-content: center; align-items: center;">
    <button type="button" data-bs-toggle="collapse" data-bs-target="#demo" style="width: 50px; height: 50px; position: relative; background-color: #060126; border: solid 0px;"><i style="color: white; font-size: 30px;" class="fa-solid fa-bars"></i></button>
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
				

				
				
				
   
				
				<div style="width: 100%; min-height: 300px; position: relative; top: 120px;">
					<h1 id=categorytitlemobile>${categoryName}게시판</h1>
					
					
				</div>
				<div style="position:relative; left:0px; top:0px;">
				<c:choose>
					<c:when test="${loginUser.userid=='hyk0910'}">
						<div style="text-align: left;">
							<div id="btns"
								style="justify-content: center; display: flex; float: right;">

								<div>
									<a href="whiskyWrite.do?" class="btn btn-sm custom-button"
										id="btnList100" style="width: 65px">글쓰기</a>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<c:if test="${loginUser.userid!='hyk0910'}">
						</c:if>
					</c:otherwise>
				</c:choose>
				</div>
				
				
				
				<div style="width: 100%; height: 450px; position: relative; margin-top: -10%;">
				    <div style="width: 100%; height: 30px; display: flex; background-color: #060126; font-size: 15px; text-align: center;">
				        <div style="width: 8%; color:white">번호</div>
				        <div style="width: 59%; color:white">제목</div>
				        <div style="width: 19%; color:white">작성일</div>
				        <div style="width: 12%; color:white">조회수</div>
				    </div>
				    <c:forEach var="whiskyList" items="${whiskyList}">
				        <div class="record">
				            <div style="width: 100%; height: 30px; display: flex; font-size: 12px; text-align: center;">
				                <div style="width: 8%;">
				                    <a href="whiskyRead.do?bno=${whiskyList.bno}">${whiskyList.bno}</a>
				                </div>
				                <div style="width: 59%;">
				                    <a href="whiskyRead.do?bno=${whiskyList.bno}">${whiskyList.title}</a>
				                </div>
				                <div style="width: 19%;">
				                    <fmt:formatDate value="${whiskyList.writedate}" pattern="yy-MM-dd HH:mm:ss" />
				                </div>
				                <div style="width: 12%;">${whiskyList.readcount}</div>
				            </div>
				        </div>
				    </c:forEach>
				    <div style="width: 100%; height: 50px; " >
				        <form method="get">
				            <div class="paging" align="center">${map.pagingString}</div>
				        </form>
				    </div>
				    <div style="width: 100%; height: 100px; ">
				        <form method="get">
				            <div class="searchbar" border="1" width="90%">
				                <div>
				                    <div align="center">
				                        <select name="searchField">
				                            <option value="title">제목</option>
				                            <option value="bcontent">내용</option>
				                        </select> 
				                        <input type="text" name="searchWord" /> 
				                        <input type="submit" class="btn btn-sm custom-button" id="btnListsch" value="검색" />
				                        <input type="hidden" name="cno" value="<%= request.getParameter("cno") %>" /> <!-- Hidden field 추가 -->
				                    </div>
				                </div>
				            </div>
				        </form>
				    </div>
				</div>
				</div>
				</form>
				</div>

   <!-- 모바일크기일때 표기 끝 -->	

	
</body>
</html>