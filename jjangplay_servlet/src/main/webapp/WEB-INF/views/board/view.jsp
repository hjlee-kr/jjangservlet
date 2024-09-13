<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글보기</title>

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
글번호 : ${param.no }, 조회수 증가 : ${param.inc }<br>
<div class="container">
  <h1><i class="fa fa-file-text-o"></i> 일반 게시판 글보기</h1>
  
  <div class="container">
    <!-- Control the column width, and how they should appear on different devices -->
    <div class="row">
      <div class="col-sm-4" style="background-color:yellow;">
      번호</div>
      <div class="col-sm-8" style="background-color:orange;">
      ${vo.no }</div>
    </div>
    <div class="row">
      <div class="col-sm-4" style="background-color:yellow;">
      제목</div>
      <div class="col-sm-8" style="background-color:orange;">
      ${vo.title }</div>
    </div>
    <div class="row">
      <div class="col-sm-4" style="background-color:yellow;">
      내용</div>
      <div class="col-sm-8" style="background-color:orange;">
      ${vo.content}</div>
    </div>
    <div class="row">
      <div class="col-sm-4" style="background-color:yellow;">
      작성자</div>
      <div class="col-sm-8" style="background-color:orange;">
      ${vo.writer}</div>
    </div>
    <div class="row">
      <div class="col-sm-4" style="background-color:yellow;">
      작성일</div>
      <div class="col-sm-8" style="background-color:orange;">
      ${vo.writeDate}</div>
    </div>
    <div class="row">
      <div class="col-sm-4" style="background-color:yellow;">
      조회수</div>
      <div class="col-sm-8" style="background-color:orange;">
      ${ vo.hit }</div>
    </div>
    <div class="row">
      <a href="updateForm.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}">
      	<button class="btn btn-primary">수정</button></a>
			<button class="btn btn-danger"
				id="deleteBtn">삭제</button>
			<a href="list.do?page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}">
			<button class="btn btn-success">리스트</button></a>
			
    </div>
 
   </div>
   
  
  <!-- The Modal -->
  <div class="modal fade" id="deleteModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">비밀번호 확인</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
			<!-- 삭제시 비밀번호 입력을 위한 form태그 사용 -->
			<form action="delete.do" method="post" id="deleteForm">
				<!-- type="hidden"은 form태그에 보이지는 않지만
				값을 같이 넘겨야 할때 사용합니다.
				값을 넘길때 가장중요한 프로퍼티는 name 이다. -->
				<input type="hidden" name="no" value="${param.no }">
				<!-- required : 반드시 작성되어야 한다는 의미 -->
				<!-- pattern ^로 시작해서 $로 끝난다. -->
				<!-- .은 \n 빼로 전부 다 사용가능 -->
				<!-- pw는 3자에서 20자 이내로 써야한다. -->
				<!-- title의 내용은 툴팁으로 모여준다. -->
				<input name="pw" required maxlength="20"
					pattern="^.{3,20}$"
					title="3~20자 입력 가능"
					placeHolder="본인 확인용 비밀번호">
				<button  class="btn btn-danger">삭제</button>
				<button type="button" class="btn btn-success"
					id="deleteCancelBtn">취소</button>
			</form>
          
          
          
          
          
        </div>
        
        
      </div>
    </div>
  </div>
  

   
   
   
   <!-- 댓글처리시작 -->
   <jsp:include page="reply.jsp"></jsp:include>
   <!-- 댓글처리끝 -->
   
   
   
</div> <!-- end of class="container" -->
</body>
</html>



