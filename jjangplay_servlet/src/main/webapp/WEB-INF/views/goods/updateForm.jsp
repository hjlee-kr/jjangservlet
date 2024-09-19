<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 수정</title>
	<!-- datepicker -->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.0/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://code.jquery.com/ui/1.14.0/jquery-ui.js"></script>

<script type="text/javascript">
//페이지가 로딩후 세팅한다.
//$(document).ready(function(){~~});
$(function() {
	console.log("jquery loading......");
	
	let now = new Date();
	let startYear = now.getFullYear();
	let yearRange = (startYear - 10) + ":" + (startYear + 10);
	
	// 날짜입력 설정 - datepicker
	$(".datepicker").datepicker({
		// 입력란의 데이터 포맷
		dateFormat: "yy-mm-dd",
		// 월 선택 입력 추가
		changeMonth: true,
		// 년 선택 입력 추가
		changeYear: true,
		// 월 선택 입력 (기본은 영어->한글로 변경)
		monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		// 달력의 요일 표시 (기본은 영어->한글로)
		dayNamesMin: ["일","월","화","수","목","금","토"],
		// 선택할 수 있는 년도의 범위
		yearRange: yearRange,
		
	});
	
	
});
</script>
</head>
<body>

<div class="container">
  <h2><i class="fa fa-edit"></i> 상품 정보 수정</h2>
  <form action="update.do" method="post">
  	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
  	<input type="hidden" name="page" value="${param.page }">
  	<input type="hidden" name="key" value="${param.key }">
  	<input type="hidden" name="word" value="${param.word }">
  	<div class="form-group">
      <label for="gno">상품번호</label>
      <input type="text" class="form-control" id="gno"
      	readonly name="gno" value="${vo.gno }">
    </div>
    <div class="form-group">
      <label for="name">상품이름</label>
      <input type="text" class="form-control" id="name"
      	pattern="^[^ .].{2.99}$" required
      	title="맨앞에 공백문자 불가, 3~100자입력"
       placeholder="상품 이름 입력" name="name" value="${vo.name }">
    </div>
    <div class="form-group">
      <label for="content">상품상세설명</label>
      <textarea class="form-control" rows="7" id="content" required
      	name="content" placeholder="내용 입력">${vo.content }</textarea>
    </div>
    <div class="form-group">
      <label for="productDate">제조일</label>
      <input type="text" class="form-control datepicker" id="productDate"
      	name="productDate" value="${vo.productDate }">
    </div>
    <div class="form-group">
      <label for="modelNo">ModelNo</label>
      <input type="text" class="form-control" id="modelNo"
      	pattern="^[A-Z0-9]{3.20}$" required
      	title="대문자와 숫자만 입력 3~20자입력"
       placeholder="모델 No 입력" name="modelNo" value="${vo.modelNo }">
    </div>
    <div class="form-group">
      <label for="company">제조사</label>
      <input type="text" class="form-control" id="company"
      	required title="10자이내 입력"
       placeholder="제조사 입력" name="company" value="${vo.company }">
    </div>
    <div class="form-group">
      <label for="delivery_cost">배송료</label>
      <input type="text" class="form-control" id="delivery_cost"
      	required title="숫자만 입력"
      	placeholder="배송료 입력" name="delivery_cost" value="${vo.delivery_cost }">
    </div>
    <button type="submit" class="btn btn-primary">수정</button>
    <button type="reset" class="btn btn-secondary">새로입력</button>
    <button type="button" class="btn btn-success" onclick="history.back();"
    	id="cencelBtn">취소</button>
  </form>
</div>
</body>
</html>



