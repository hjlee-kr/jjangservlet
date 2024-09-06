<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!-- 데이터는 DispatcherServlet에 담겨있다(request) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 리스트</title>

<!-- 4. 우리가 만든 라이브러리 등록 -->
<script type="text/javascript" src="boardInputUtil.js"></script>

<script type="text/javascript">
$(function(){
	//  2. 라이브러리 등록확인 
	console.log("jquery loading......");
	
	// 이벤트 처리
	$("#perPageNum").change(function(){
		$("#searchForm").submit();
	});
	
	// 검색데이터 세팅
	$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
	$("#perPageNum")
		.val("${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");
	
});
</script>

</head>
<body>

<div class="container p-3 my-3">
	<h1><i class="fa fa-align-justify"></i> 이미지 게시판 리스트</h1>
	<form action="list.do" id="searchForm">
		<div class="row">
			<div class="col-md-8">
	  			<div class="input-group mt-3 mb-3">
					<div class="input-group-prepend">
						<select class="form-control" id="key" name="key">
							<option value="t">제목</option>
					        <option value="c">내용</option>
					        <option value="w">작성자</option>
					        <option value="tc">제목/내용</option>
					        <option value="tw">제목/작성자</option>
					        <option value="cw">내용/작성자</option>
					        <option value="tcw">모두</option>
						</select>
					</div>
		      		<input type="text" class="form-control" placeholder="검색어입력"
	      				id="word" name="word" value="${pageObject.word }">
					<div class="input-group-prepend">
						<button type="submit" class="btn btn-primary">
							<i class="fa fa-search"></i></button>
					</div>
			    </div>
			</div> <!-- end of class="col-md-8" -->
			<div class="col-md-4">
				<div class="input-group mt-3 mb-3">
				  <div class="input-group-prepend">
				    <span class="input-group-text">Rows/Page</span>
				  </div>
				  <select id="perPageNum" name="perPageNum"
				   class="form-control">
				   		<option>10</option>
				   		<option>15</option>
				   		<option>20</option>
				   		<option>25</option>
				  </select>
				</div>
			</div> <!-- end of class="col-md-4" -->
		</div><!-- end of class="row" -->
	</form>

	<c:if test="${empty list }">
		<h4>데이터가 존재하지 않습니다.</h4>
	</c:if>
	<c:if test="${!empty list }">
		<div class="row">
			<!-- 이미지 list의 데이터가 있는 만큼 표시하는 처리 시작 -->
			<c:forEach items="${list }" var="vo" varStatus="vs">
				<!-- 줄바꿈처리 - 3개를 표시하면 줄을 바꾼다. -->
				<c:if test="${(vs.index != 0) && (vs.index%3 == 0) }">
					${"</div>"}
					${"<div class='row'>"}
				</c:if>
				<div class="col-md-4">
					<div class="card" style="width:100%">
						<div>
					  	<img class="card-img-top" src="${vo.fileName }" alt="Card image">
					  </div>
					  <div class="card-body">
					    <h4 class="card-title">
					    	<span class="float-right">${vo.writeDate }</span>
					    	${vo.name }(${vo.id })
					    </h4>
					    <p class="card-text">
					    	<span class="no">${vo.no }</span>.${vo.title }
							</p>
					  </div>
					</div><!-- end of card -->
				</div>
			</c:forEach>
			<!-- 이미지 데이터 반복 표시 끝 -->
		</div>
	</c:if><!-- 데이터 존재했을때 처리 끝 -->

	<div>
		<pageNav:pageNav listURI="list.do" pageObject="${pageObject}"></pageNav:pageNav>
	</div>

</div>
</body>
</html>