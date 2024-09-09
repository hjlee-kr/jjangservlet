<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 글 수정 폼</title>

<!-- 4. 우리가 만든 라이브러리 등록 -->
<script type="text/javascript" src="boardInputUtil.js"></script>

<!-- 2.라이브러리 확인 -->
<script type="text/javascript">
$(function(){
	console.log("jquery loading.........");
});
</script>


</head>
<body>
글번호 : ${param.no }<br>

<div class="container">
  <h2><i class="fa fa-pencil-square-o"></i> 이미지 게시판 글 수정 폼</h2>
  <div class="alert alert-info">
		이미지를 제외한 나머지 텍스트 데이터를 수정합니다.    
		이미지는 이미지 게시판 보기 화면에서 이미지안에 바꾸기 버튼을 누르세요.
  </div>
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
    <button type="submit" class="btn btn-primary">수정</button>
    <button type="reset" class="btn btn-secondary">다시쓰기</button>
    <button type="button" class="btn btn-success"
    	onclick="history.back()">취소</button>
  </form>
</div>
</body>
</html>