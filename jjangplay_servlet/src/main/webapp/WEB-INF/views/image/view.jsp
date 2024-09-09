<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 글보기</title>

<!-- 2.라이브러리 확인 -->
<script type="text/javascript">
// jquery: $(ducument).ready(function(){~~~});
// 페이지가 로딩완료 후 세팅이 진행된다.
$(function(){
	console.log("jquery loading......");
	
	$("#deleteBtn").click(function(){
		console.log("deleteBtn event......");
		// 비밀번호 입력창 clear
		$("#pw").val("");
		$("#deleteModal").modal("show");
	});
	
	$("#deleteCancelBtn").click(function(){
		console.log("deleteCancelBtn event......");
		$("#pw").val("");
		$("#deleteModal").modal("hide");
	});
	
	
	
	
	
});
</script>


</head>
<body>
글번호 : ${param.no }<br>
<div class="container">
  <h1><i class="fa fa-file-text-o"></i> 이미지 글보기</h1>
  <div class="card">
	  <div class="card-header">
	  	<b>${vo.no }. ${vo.title }</b> 
	  </div>
	  <div class="card-body">
	  	<div class="card">
			  <img class="card-img-top" src="${vo.fileName }" alt="image">
			  <div class="card-img-overlay">
			  	<!-- Button to Open the Modal -->
			  	<c:if test="${login.id == vo.id }">
						<button type="button" class="btn btn-primary"
						 data-toggle="modal" data-target="#imageChangeModal">
						  이미지변경
						</button>
					</c:if>
					<a href="${vo.fileName }" class="btn btn-success" download>다운로드</a>
			  </div>
			  <div class="card-body">
			    <p class="card-text">
			    	<pre>${vo.content }</pre>
					</p>
			  </div>
			</div>
		</div>
	  <div class="card-footer">
	  	<span class="float-right">${vo.writeDate }</span>
	  	${vo.name }(${vo.id })
	  </div>
	</div>


	<!-- The Modal -->
	<div class="modal" id="imageChangeModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">바꿀이미지 선택하기</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      
	      <form action="imageChange.do" method="post" enctype="multipart/form-data">
	      	<!-- 숨겨서 넘겨야 할 데이터 : 이미지번호, 현재화일이름(삭제를 위해서) -->
	      	<input name="no" value="${vo.no }" type="hidden">
	      	<input name="deleteFileName" value="${vo.fileName }" type="hidden">
					<!-- 페이지 정보도 넘겨줍니다. -->
					<input name="page" value="${param.page }" type="hidden">
					<input name="perPageNum" value="${param.perPageNum }" type="hidden">
					<input name="key" value="${param.key }" type="hidden">
					<input name="word" value="${param.word }" type="hidden">
		      <!-- Modal body -->
		      <div class="modal-body">
		        <div class="form-group">
				      <label for="imageFile">첨부이미지</label>
				      <input type="file" class="form-control"
				       id="imageFile" required
				      	name="imageFile">
				    </div>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		      	<button class="btn btn-primary">바꾸기</button>
		        <button type="button" class="btn btn-danger"
		         data-dismiss="modal">취소</button>
		      </div>
				</form>
	    </div>
	  </div>
	</div>

   
</div> <!-- end of class="container" -->
</body>
</html>



