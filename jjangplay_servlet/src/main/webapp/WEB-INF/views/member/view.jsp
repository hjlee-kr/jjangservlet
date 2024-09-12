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
<script type="text/javascript">
$(function() {
	
	// 탈퇴를 위한 모달창을 보여주고 닫는 이벤트
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
	// 이벤트 끝 (탈퇴 모달창)
	
});
</script>
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
		  	<button type="button" class="btn btn-danger"
		  		id="deleteBtn">탈퇴</button>
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


  <!-- The Modal - 삭제를 위한 -->
  <div class="modal fade" id="deleteModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">탈퇴를 위한 비밀번호를 입력하세요</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
					<!-- 삭제시 비밀번호 입력을 위한 form태그 사용 -->
					<form action="delete.do" method="post" id="deleteForm">
						<!-- type="hidden"은 form태그에 보이지는 않지만
						값을 같이 넘겨야 할때 사용합니다.
						값을 넘길때 가장중요한 프로퍼티는 name 이다. -->
						<input type="hidden" name="id" value="${vo.id }">
						<!-- required : 반드시 작성되어야 한다는 의미 -->
						<!-- pattern ^로 시작해서 $로 끝난다. -->
						<!-- .은 \n 빼로 전부 다 사용가능 -->
						<!-- pw는 3자에서 20자 이내로 써야한다. -->
						<!-- title의 내용은 툴팁으로 모여준다. -->
						<input name="pw" required maxlength="20"
							pattern="^.{3,20}$"
							title="3~20자 입력 가능"
							placeHolder="본인 확인용 비밀번호">
						<button  class="btn btn-danger">탈퇴</button>
						<button type="button" class="btn btn-success"
							id="deleteCancelBtn">취소</button>
					</form>
        </div>
      </div>
    </div>
  </div>
  

</body>
</html>