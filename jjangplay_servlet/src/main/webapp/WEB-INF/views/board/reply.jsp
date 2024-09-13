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
  		 pageObject="${replyPageObject}" query="&inc=0"></pageNav:replayPageNav>
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
        	<!-- 화면에 보여줄 필요는 없지만 처리를 위해서 값을 넘겨줘야 할때는
        		type="hidden"으로 설정하여 기록합니다. -->
          	<input type="hidden" name="rno" id="rno">
          	<input type="hidden" name="no" value="${param.no }">
          	<input type="hidden" name="page" value="${param.page }">
          	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
          	<!-- 검색어관련 -->
          	<input type="hidden" name="key" value="${param.key }">
          	<input type="hidden" name="word" value="${param.word }">
        <!-- Modal body 시작 -->
        <div class="modal-body">
          <!-- 내용 / 작성자 / 비밀번호 -->
          <div class="form-group" id="contentDiv">
          	<label for="content">내용</label>
          	<textarea class="form-group" rows="3" id="content"
          	name="content"></textarea>
          </div>
          <div class="form-group" id="writerDiv">
          	<label for="writer">작성자</label>
          	<input type="text" class="form-group"
          	id="writer" name="writer">
          </div>
          <div class="form-group" id="pwDiv">
          	<label for="pwr">비밀번호</label>
          	<input type="password" class="form-group"
          	id="pwr" name="pw">
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
		// 모달창에 보이는 데이터만 지운다.
		// hidden세팅한것이 지워지지 않도록 주의
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
		// 모달창에 보이는 데이터만 지운다.
		// hidden세팅한것이 지워지지 않도록 주의
		$("#boardReplyForm")
		.find(".form-group->input .form-group->textarea").val("");
		
		// 댓글번호, 내용, 작성자데이터 수집해서 value값에 세팅
		let replyDataRow = $(this).closest(".replyDataRow");
		let rno = replyDataRow.data("rno");
		let content = replyDataRow.find(".replyContent").text();
		let writer = replyDataRow.find(".replyWriter").text();
		
		// 모달창 입력난에 기존값 적는다.
		$("#rno").val(rno);
		$("#content").val(content);
		$("#writer").val(writer);
		
		// 버튼선택
		// 먼저 다 보이도록 설정
		$("#boardReplyForm button").show();
		// 필요없는버튼은 안보이도록 설정
		$("#replyModalWriteBtn, #replyModalDeleteBtn").hide();
		
		// 모달창이 보이도록
		$("#boardReplyModal").modal("show");
	});
	// 댓글 삭제
	$(".replyDeleteBtn").click(function(){
		// 제목을 댓글 삭제
		$("#boardReplyModal").find(".modal-title").text("댓글삭제");
		// input / text 선택
		$("#boardReplyForm").find(".form-group").hide();
		$("#pwDiv").show();
		$("#pwr").show();
		// 데이터 지우기 (빈데이터를 넣는다.)
		// 모달창에 보이는 데이터만 지운다.
		// hidden세팅한것이 지워지지 않도록 주의
		$("#boardReplyForm")
		.find(".form-group->input .form-group->textarea").val("");
		
		// 댓글번호
		let replyDataRow = $(this).closest(".replyDataRow");
		let rno = replyDataRow.data("rno");
		// 모달창 입력난에 기존값 적는다.
		$("#rno").val(rno);
		//$("rno").val($(this).closest(".replyDataRow").data("rno"));
		
		// 버튼선택
		// 먼저 다 보이도록 설정
		$("#boardReplyForm button").show();
		// 필요없는버튼은 안보이도록 설정
		$("#replyModalWriteBtn, #replyModalUpdateBtn").hide();
		
		// 모달창이 보이도록
		$("#boardReplyModal").modal("show");
	});
	// 모달창 안의 버튼을 클릭하는 이벤트 처리
	$("#replyModalWriteBtn").click(function(){
		console.log("replyModalWriteBtn click event");
		// 데이터를 전송해서 처리하는 경로 세팅
		$("#boardReplyForm").attr("action", "/boardreply/write.do");
		// 데이터 전송하고 처리
		$("#boardReplyForm").submit();
	});
	$("#replyModalUpdateBtn").click(function(){
		// 데이터를 전송해서 처리하는 경로 세팅
		$("#boardReplyForm").attr("action", "/boardreply/update.do");
		// 데이터 전송하고 처리
		$("#boardReplyForm").submit();
	});
	$("#replyModalDeleteBtn").click(function(){
		// 데이터를 전송해서 처리하는 경로 세팅
		$("#boardReplyForm").attr("action", "/boardreply/delete.do");
		// 데이터 전송하고 처리
		$("#boardReplyForm").submit();
	});
	
	
	
});
</script>
  
  
  
  
  
  
  
  
  
  
  