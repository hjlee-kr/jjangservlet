<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 등록 폼</title>
</head>
<body>

<div class="container">
  <h2><i class="fa fa-edit"></i> 이미지 게시판 글 등록 폼</h2>
  <form action="write.do" method="post" enctype="multipart/form-data">
  	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
    <div class="form-group">
      <label for="title">제목</label>
      <input type="text" class="form-control" id="title"
      	pattern="^[^ .].{2.99}$" required
      	title="맨앞에 공백문자 불가, 3~100자입력"
       placeholder="제목 입력" name="title">
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <textarea class="form-control" rows="7" id="content" required
      	name="content" placeholder="내용 입력"></textarea>
    </div>
    <div class="form-group">
      <label for="imageFile">첨부이미지</label>
      <input type="file" class="form-control" id="imageFile" required
      	name="imageFile">
    </div>
    <button type="submit" class="btn btn-primary">등록</button>
    <button type="reset" class="btn btn-secondary">새로입력</button>
    <button type="button" class="btn btn-success" onclick="history.back();"
    	id="cencelBtn">취소</button>
  </form>
</div>
</body>
</html>



