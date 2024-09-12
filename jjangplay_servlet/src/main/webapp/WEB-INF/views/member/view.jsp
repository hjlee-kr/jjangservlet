<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${vo.id }] 회원정보</title>
<style type="text/css">
	#infoDiv>.row{
		padding: 10px;
		border-top: 1px dotted #ccc;
		margin: 0 10px;
	}
</style>
</head>
<body>
<div class="container">
	<div class="card">
	  <div class="card-header">
	  	<div>
	  		${vo.name } (${vo.id })
	  	</div>
		</div>
	  <div class="card-body" id="infoDiv">
	  	<div class="text-center">
	  		<c:if test="${ empty vo.photo}">
		  		<i class="fa fa-user-circle-o" style="font-size:100px"></i>
	  		</c:if>
	  		<c:if test="${ !empty vo.photo}">
		  		<div class="card">
					  <img class="card-img-top" src="${vo.photo }" alt="image"
					  	 style="width:100px; height:100px;">
							<a href="${vo.photo }" class="btn btn-success" download>다운로드</a>
					</div>
	  		</c:if>
	  		<!-- Button to Open the Modal -->
				<button type="button" class="btn btn-primary"
					 data-toggle="modal" data-target="#imageChangeModal">
				  이미지변경
				</button>
	  	</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 성별</div>
			  <div class="col-md-9">${vo.gender }</div>
			</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 생년월일</div>
			  <div class="col-md-9">${vo.birth }</div>
			</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 연락처</div>
			  <div class="col-md-9">${vo.tel }</div>
			</div>
			<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 이메일</div>
			  <div class="col-md-9">${vo.email }</div>
			</div>
			<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 가입일</div>
			  <div class="col-md-9">${vo.regDate }</div>
			</div>
			<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 최근접속일</div>
			  <div class="col-md-9">${vo.conDate }</div>
			</div>
			<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 회원등급</div>
			  <div class="col-md-9">${vo.gradeName }</div>
			</div>
			<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 회원상태</div>
			  <div class="col-md-9">${vo.status }</div>
			</div>
		</div>
	  <div class="card-footer">
	  	<c:if test="${vo.id == login.id }">
		  	<a href="/member/updateForm.do?id=${vo.id }" class="btn btn-primary">수정</a>
		  	<a href="/member/delete.do?id=${vo.id }" class="btn btn-danger">탈퇴</a>
	  	</c:if>
	  	<button onclick="history.back()" class="btn btn-success">되돌아가기</button>
	  </div>
	</div>
</div> <!-- end of class="container" -->

	<!-- The Modal -->
	<div class="modal" id="imageChangeModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">바꿀사진 선택하기</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      
	      <form action="changePhoto.do" method="post" enctype="multipart/form-data">
	      	<!-- 숨겨서 넘겨야 할 데이터 : 회원id, 현재화일이름(삭제를 위해서) -->
	      	<input name="id" value="${vo.id }" type="hidden">
	      	<input name="deleteFileName" value="${vo.photo }" type="hidden">
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


</body>
</html>