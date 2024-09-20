<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품상세페이지</title>

<!-- 2.라이브러리 확인 -->
<script type="text/javascript">
// jquery: $(ducument).ready(function(){~~~});
// 페이지가 로딩완료 후 세팅이 진행된다.
$(function(){
	console.log("jquery loading......");
	
	$('[data-toggle="tooltip"]').tooltip();   

	
	
});
</script>


</head>
<body>
<div class="container">
  <h1><i class="fa fa-file-text-o"></i> 제품상세페이지</h1>
  <div class="card">
	  <div class="card-header">
	  	<b>${vo.gno }. ${vo.name }</b> 
	  </div>
	  <div class="card-body">
	  	<div class="card">
	  		<div class="row">
	  			<div class="col-sm-6">
				  	<img class="card-img-top" src="${vo.imageName }" alt="image">
				  </div>
				  <div class="col-sm-6">
				  	<div class="row">
				  		<div class="col-sm-3">제품번호</div>
				  		<div class="col-sm-9">${vo.gno }</div>
				  	</div>
				  	<div class="row">
				  		<div class="col-sm-3">제품명</div>
				  		<div class="col-sm-9"><h4>${vo.name }</h4></div>
				  	</div>
				  	<div class="row">
				  		<div class="col-sm-3">정가</div>
				  		<div class="col-sm-9"
				  		 style="text-decoration: line-through;"
				  		 >${vo.std_price }</div>
				  	</div>
				  	<div class="row">
				  		<div class="col-sm-3">할인가</div>
				  		<div class="col-sm-9"><h4>${vo.discount }</h4></div>
				  	</div>	
				  	<div class="row" style="padding:10px 20px;">
				  		<pre>${vo.content }</pre>
				  	</div>
				  </div>
	  		</div>
			</div>
		</div>
	</div>
	<!-- a tag : 데이터를 클릭하면 href 정보를 가져와서 페이지이동 -->
	<a href="list.do?page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
		class="btn btn-info">리스트</a>
	
	
</div> <!-- end of class="container" -->


</body>
</html>



