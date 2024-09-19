<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 가격 등록</title>
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
  <h2><i class="fa fa-edit"></i> 상품 가격 등록</h2>
  <form action="writePrice.do" method="post">
  	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
  	<input type="hidden" name="page" value="${param.page }">
  	<input type="hidden" name="key" value="${param.key }">
  	<input type="hidden" name="word" value="${param.word }">
    <div class="form-group">
      <label for="gno">상품번호</label>
      <input type="text" class="form-control" id="gno"
      	readonly name="gno" value="${param.gno }">
    </div>
    <div class="form-group">
      <label for="name">상품이름</label>
      <input type="text" class="form-control" id="name"
      	readonly name="name" value="${param.name }">
    </div>
    <div class="form-group">
      <label for="std_price">상품가격</label>
      <input type="text" class="form-control" id="std_price"
      	required title="숫자만 입력"
      	placeholder="상품 정가 입력" name="std_price">
    </div>
    <div class="form-group">
      <label for="discount">할인가격</label>
      <input type="text" class="form-control" id="discount"
      	title="숫자만 입력"
      	placeholder="상품 할인가 입력" name="discount">
    </div>
    <div class="form-group">
      <label for="rate">할인율</label>
      <input type="text" class="form-control" id="rate"
      	title="할인율 입력"
      	placeholder="상품 할인율 입력" name="rate">
    </div>
    <div class="form-group">
      <label for="startDate">판매시작일</label>
      <input type="text" class="form-control datepicker" id="startDate"
      	name="startDate">
    </div>
    <div class="form-group">
      <label for="endDate">판매종료일</label>
      <input type="text" class="form-control datepicker" id="endDate"
      	name="endDate">
    </div>
    <button type="submit" class="btn btn-primary">등록</button>
    <button type="reset" class="btn btn-secondary">새로입력</button>
    <button type="button" class="btn btn-success" onclick="history.back();"
    	id="cencelBtn">취소</button>
  </form>
</div>
</body>
</html>



