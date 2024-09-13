<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글 등록 폼</title>

<!-- 4. 우리가 만든 라이브러리 등록 -->
<script type="text/javascript" src="boardInputUtil.js"></script>

<!-- 2. 라이브러리 등록확인 -->
<script type="text/javascript">
//페이지가 로딩후 세팅한다.
//$(document).ready(function(){~~});
$(function() {
	console.log("jquery loading......");
	
	//5. 이벤트 처리
	$("#writeForm").submit(function() {
		console.log("writeForm submit event......");
		
		//6. 필수항목체크 (제목, 내용, 작성자, 비밀번호, 비밀번호확인)
		// isEmpty(객체, 항목이름, 트림유무)
		if (isEmpty("#title", "제목", 1)) return false;
		if (isEmpty("#content", "내용", 1)) return false;
		if (isEmpty("#writer", "작성자", 1)) return false;
		if (isEmpty("#pw", "비밀번호", 1)) return false;
		if (isEmpty("#pw2", "비밀번호확인", 1)) return false;
		
		//7. 길이체크
		// lengthCheck(객체, 항목이름, 최소, 최대, 트림유무)
		if (lengthCheck("#title", "제목", 3, 100, 1)) return false;
		if (lengthCheck("#content", "내용", 3, 600, 1)) return false;
		if (lengthCheck("#writer", "작성자", 2, 10, 1)) return false;
		if (lengthCheck("#pw", "비밀번호", 3, 20, 1)) return false;
		if (lengthCheck("#pw2", "비밀번호확인", 3, 20, 1)) return false;
	});

	$("#cencelBtn").click(function(){
		console.log("cancelBtn event........");
		
		history.back();
	});
	
	
});
</script>

</head>
<body>
<!-- 3. 내용작성 -->
<div class="container">
  <h2><i class="fa fa-edit"></i> 일반 게시판 글 등록 폼</h2>
  <form action="write.do" method="post" id="writeForm">
  	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
    <div class="form-group">
      <label for="title">제목</label>
      <input type="text" class="form-control" id="title"
       placeholder="제목 입력" name="title">
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <textarea class="form-control" rows="7" id="content"
      	name="content" placeholder="내용 입력"></textarea>
    </div>
    <div class="form-group">
      <label for="writer">작성자</label>
      <input type="text" class="form-control" id="writer"
       placeholder="작성자 입력" name="writer">
    </div>
    <div class="form-group">
      <label for="pw">비밀번호</label>
      <input type="password" class="form-control" id="pw"
       placeholder="비밀번호 입력" name="pw">
    </div>
    <div class="form-group">
      <label for="pw2">비밀번호 확인</label>
      <input type="password" class="form-control" id="pw2"
       placeholder="비밀번호 확인 입력">
    </div>
    <button type="submit" class="btn btn-primary">등록</button>
    <button type="reset" class="btn btn-secondary">새로입력</button>
    <button type="button" class="btn btn-success"
    	id="cencelBtn">취소</button>
  </form>
</div>
</body>
</html>



