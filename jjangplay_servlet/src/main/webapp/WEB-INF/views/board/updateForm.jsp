<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 글 수정 폼</title>

<!-- 4. 우리가 만든 라이브러리 등록 -->
<script type="text/javascript" src="boardInputUtil.js"></script>

<!-- 2.라이브러리 확인 -->
<script type="text/javascript">
$(function(){
	console.log("jquery loading.........");
	
	// 5.이벤트 실행
	$("#updateForm").submit(function(){
		console.log("updateForm event......");
		
		// 6.필수항목체크 (제목, 내용, 작성자, 비밀번호확인)
		// isEmpty(객체, 항목, 트림유무)
		if (isEmpty("#title", "제목", 1)) return false;
		if (isEmpty("#content", "내용", 1)) return false;
		if (isEmpty("#writer", "작성자", 1)) return false;
		if (isEmpty("#pw", "비밀번호확인", 0)) return false;
		
		// 7.길이체크 (제목, 내용, 작성자, 비밀번호확인)
		// lengthCheck(객체, 항목, 최소, 최대, 트림유무)
		if (lengthCheck("#title", "제목", 3, 100, 1)) return false;
		if (lengthCheck("#content", "내용", 3, 100, 1)) return false;
		if (lengthCheck("#writer", "작성자", 3, 100, 1)) return false;
		if (lengthCheck("#pw", "비밀번호확인", 3, 100, 0)) return false;
		
	});
	
	$("#cancelBtn").click(function(){
		console.log("cancelBtn event........");
		
		history.back();
	});
	
	
});
</script>


</head>
<body>
글번호 : ${param.no }<br>

<div class="container">
  <h2><i class="fa fa-pencil-square-o"></i> 일반 게시판 글 수정 폼</h2>
  <form action="update.do" method="post" id="updateForm">
  	<input type="hidden" name="page" value="${param.page }">
  	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
  	<input type="hidden" name="key" value="${param.key }">
  	<input type="hidden" name="word" value="${param.word }">
    <div class="form-group">
      <label for="no">번호</label>
      <input type="text" class="form-control"
       id="no" name="no" readonly value="${ vo.no }">
    </div>
    <div class="form-group">
      <label for="title">제목</label>
      <input type="text" class="form-control"
       id="title" name="title" value="${ vo.title }"
       pattern="^[^ .].{2,99}$"
       title="맨앞에 공백문자 불가 : 3~100자 입력"
       placeholder="제목입력: 3자이상 100자 이내"
       >
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <pre>
      <textarea class="form-control" rows="7"
       id="content" name="content">${ vo.content }</textarea>
      </pre>
    </div>
    <div class="form-group">
      <label for="writer">작성자</label>
      <input type="text" class="form-control"
       id="writer" name="writer"value="${ vo.writer }"
       pattern="^[a-zA-Z가-힝]{2,10}$"
       title="한글과 영어만 입력 : 2~10자 입력"
       placeholder="이름은 한글과 영어만 입력가능합니다."
       >
    </div>
    <div class="form-group">
      <label for="pw">비밀번호확인</label>
      <input type="password" class="form-control" id="pw"
       placeholder="비밀번호입력" name="pw"
       pattern="^.{3,20}$"
       title="3~20자 입력가능"
       placeholder="본인확인용 비밀번호">
    </div>
    <button type="submit" class="btn btn-primary">수정</button>
    <button type="reset" class="btn btn-secondary">다시쓰기</button>
    <button type="button" class="btn btn-success"
    	id="cancelBtn">취소</button>
  </form>
</div>
</body>
</html>