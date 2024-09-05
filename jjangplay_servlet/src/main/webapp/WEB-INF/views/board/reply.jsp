<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<div class="card">
  <div class="card-header">
  	<span class="btn btn-primary float-right" id="replyWriteBtn">등록</span>
  	<h3>댓글리스트</h3>
  </div>
  <div class="card-body">
  	<c:if test="${empty replyList }">
  		데이터가 존재하지 않습니다.
  	</c:if>
  	<c:if test="${!empty replyList }">
  		<!-- 데이터가 있는 만큼 반복 처리 -->
  		<c:forEach items="${replyList }" var="replyVO">
		  	<div class="card replyDataRow" data-rno="${replyVO.rno}"
		  		style="margin: 5px 0;">
		  		<div class="card-header">
		  			<span class="float-right">${replyVO.writeDate }</span>
		  			<b class="replyWriter">${replyVO.writer }</b>
		  		</div>
		  		<div class="card-body">
		  			<pre class="replyContent">${replyVO.content }</pre>
		  		</div>
		  		<div class="card-footer">
		  			<button class="btn btn-sucess replyUpdateBtn">수정</button>
		  			<button class="btn btn-dager replyDeleteBtn">삭제</button>
		  		</div>
		  	</div>
   		</c:forEach>
   		<!-- 데이터 반복 처리 끝 -->
  	</c:if>
  </div>
  <div class="card-footer">
  	<pageNav:replayPageNav listURI="view.do"
  		 pageObject="${replyPageObject}"></pageNav:replayPageNav>
  </div>
</div>

<!-- 댓글 등록 / 수정 / 삭제를 위한 모달창 -->
  <!-- The Modal -->
  <div class="modal fade" id="boardReplyModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
        	<!-- 버튼(등록, 수정, 삭제)에따라서 title을 변경-->
          <h4 class="modal-title">Modal Heading</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <form id="boardReplyForm" method="post">
        <!-- Modal body 시작 -->
        <div class="modal-body">
          	<input type="hidden" name="rno" id="rno">
          	<input type="hidden" name="page" id="page">
          	<input type="hidden" name="perPageNum" id="perPageNum">
          
          
          <!-- 내용 / 작성자 / 비밀번호 -->
          <div class="form-group" id="contentDiv">
          	<label for="content">내용</label>
          	<textarea class="form-group" rows="3" id="content"
          	name="content"></textarea>
          </div>
          <div class="form-group" id="writerDiv">
          	<label for="writer">작성자</label>
          	<input type="text" class="form-gruop"
          	id="writer" name="writer">
          </div>
          <div class="form-group" id="pwDiv">
          	<label for="pw">비밀번호</label>
          	<input type="password" class="form-gruop"
          	id="pw" name="pw">
          </div>
        </div>
        <!-- Modal body 끝 -->
        
        <!-- Modal footer 시작 -->
        <div class="modal-footer">
        	<button class="btn btn-primary" type="button"
        		id="replyModalWriteBtn">등록</button>
        	<button class="btn btn-success" type="button"
        		id="replyModalUpdateBtn">수정</button>
        	<button class="btn btn-danger" type="button"
        		id="replyModalDeleteBtn">삭제</button>
          <button type="button" class="btn btn-warning"
           data-dismiss="modal">취소</button>
        </div>
        <!-- Madal footer 끝 -->
        </form>
      </div>
    </div>
  </div>
  
<script type="text/javascript">
$(function() {
	// 이벤트 처리 (Modal화면)
	// 댓글 등록
	$("#replyWriteBtn").click(function(){
		// 제목을 댓글 등록
		$("#boardReplyModal").find(".modal-title").text("댓글등록");
		// input / text 선택
		$("#boardReplyForm").find(".form-group").show();
		// 데이터 지우기 (빈데이터를 넣는다.)
		$("#boardReplyForm")
		.find(".form-group->input .form-group->textarea").val("");
		
		// 버튼선택
		// 먼저 다 보이도록 설정
		$("#boardReplyForm button").show();
		// 필요없는버튼은 안보이도록 설정
		$("#replyModalUpdateBtn, #replyModalDeleteBtn").hide();
		
		// 모달창이 보이도록
		$("#boardReplyModal").modal("show");
	});
	// 댓글 수정
	$(".replyUpdateBtn").click(function(){
		// 제목을 댓글 등록
		$("#boardReplyModal").find(".modal-title").text("댓글수정");
		// input / text 선택
		$("#boardReplyForm").find(".form-group").show();
		// 데이터 지우기 (빈데이터를 넣는다.)
		$("#boardReplyForm")
		.find(".form-group->input .form-group->textarea").val("");
		
		// 버튼선택
		// 먼저 다 보이도록 설정
		$("#boardReplyForm button").show();
		// 필요없는버튼은 안보이도록 설정
		$("#replyModalUpdateBtn, #replyModalDeleteBtn").hide();
		
		// 모달창이 보이도록
		$("#boardReplyModal").modal("show");
	});
	
	
	
});
</script>
  
  
  
  
  
  
  
  
  
  
  