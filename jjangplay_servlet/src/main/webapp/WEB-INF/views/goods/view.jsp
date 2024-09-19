<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 보기</title>

<!-- 2.라이브러리 확인 -->
<script type="text/javascript">
// jquery: $(ducument).ready(function(){~~~});
// 페이지가 로딩완료 후 세팅이 진행된다.
$(function(){
	console.log("jquery loading......");
	
	$('[data-toggle="tooltip"]').tooltip();   
	
	$("#deleteBtn").click(function(){
		console.log("deleteBtn event......");
		if (!confirm("정말삭제하시겠습니까?")) return false;
	});
	
	
	
});
</script>


</head>
<body>
<div class="container">
  <h1><i class="fa fa-file-text-o"></i> 상품 상세 보기</h1>
  <div class="card">
	  <div class="card-header">
	  	<div class="row">
	  		<div class="col-md-3 text-center font-weight-bolder">상품명</div>
	  		<div class="col-md-9 font-weight-bolder">${vo.name }</div>
	  	</div>
	  	<div class="row">
	  		<div class="col-md-3 text-center font-weight-bolder">상품번호</div>
	  		<div class="col-md-9 font-weight-bolder">${vo.gno }</div>
	  	</div>
	  </div>
	  <div class="card-body">
	  	<div class="card">
	  	${imageName}
			  <img class="card-img-top" src="${vo.imageName }" alt="image">
			  <div class="card-img-overlay">
			  	<!-- Button to Open the Modal -->
					<button type="button" class="btn btn-primary"
					 data-toggle="modal" data-target="#imageChangeModal">
					  이미지변경
					</button>
			  </div>
			  <div class="card-body">
			  	<div class="row">
			  		<div class="col-md-3 text-center">상품설명</div>
			  		<div class="col-md-9"><pre>${vo.content }</pre></div>
			  	</div>
			  	<div class="row">
			  		<div class="col-md-3 text-center">모델No</div>
			  		<div class="col-md-9">${vo.modelNo }</div>
			  	</div>
			  	<div class="row">
			  		<div class="col-md-3 text-center">제조사</div>
			  		<div class="col-md-9">${vo.company }</div>
			  	</div>
			  	<div class="row">
			  		<div class="col-md-3 text-center">제조일</div>
			  		<div class="col-md-9">${vo.productDate }</div>
			  	</div>
			  	<div class="row">
			  		<div class="col-md-3 text-center">작성일</div>
			  		<div class="col-md-9">${vo.writeDate }</div>
			  	</div>
			  	<div class="row">
			  		<div class="col-md-3 text-center">배송비</div>
			  		<div class="col-md-9">${vo.delivery_cost }원</div>
			  	</div>
			  	<!-- pno가 있으면 가격정보가 등록이 된것이다. -->
			  	<c:if test="${!empty vo.pno }">
			  		<div class="row">
				  		<div class="col-md-3 text-center">정가</div>
				  		<div class="col-md-9">${vo.std_price }원</div>
				  	</div>
			  		<div class="row">
				  		<div class="col-md-3 text-center">할인가</div>
				  		<div class="col-md-9">${vo.discount }원</div>
				  	</div>
			  		<div class="row">
				  		<div class="col-md-3 text-center">할인율</div>
				  		<div class="col-md-9">${vo.rate }</div>
				  	</div>
			  		<div class="row">
				  		<div class="col-md-3 text-center">판매시작일</div>
				  		<div class="col-md-9">${vo.startDate }</div>
				  	</div>
			  		<div class="row">
				  		<div class="col-md-3 text-center">판매종료일</div>
				  		<div class="col-md-9">${vo.endDate }</div>
				  	</div>
			  	</c:if>
			  </div>
			</div>
		</div>
	</div>
	<!-- a tag : 데이터를 클릭하면 href 정보를 가져와서 페이지이동 -->

		<a href="updateForm.do?gno=${param.gno }&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
			class="btn btn-primary" title="이미지를 제외한 정보만 수정합니다."
			data-toggle="tooltip" data-placement="top" id="updateBtn">수정</a>
		<a href="delete.do?no=${param.no }&deleteFileName=${vo.imageName}&perPageNum=${param.perPageNum}"
			class="btn btn-danger" id="deleteBtn">삭제</a>
		<a href="list.do?no=${param.no }&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
			class="btn btn-info">리스트</a>
		<c:if test="${!empty vo.pno }">
			<a href="updatePriceForm.do?gno=${param.gno }&name=${vo.name }&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				class="btn btn-success">가격정보수정</a>
		</c:if>
		<c:if test="${empty vo.pno }">
			<a href="writePriceForm.do?gno=${param.gno }&name=${vo.name }&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				class="btn btn-success">가격정보등록</a>
		</c:if>


</div> <!-- end of class="container" -->






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
	      	<input name="gno" value="${vo.gno }" type="hidden">
	      	<input name="deleteFileName" value="${vo.imageName }" type="hidden">
					<!-- 페이지 정보도 넘겨줍니다. -->
					<input name="page" value="${param.page }" type="hidden">
					<input name="perPageNum" value="${param.perPageNum }" type="hidden">
					<input name="key" value="${param.key }" type="hidden">
					<input name="word" value="${param.word }" type="hidden">
		      <!-- Modal body -->
		      <div class="modal-body">
		        <div class="form-group">
				      <label for="imageName">첨부이미지</label>
				      <input type="file" class="form-control"
				       id="imageName" required
				      	name="imageName">
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



