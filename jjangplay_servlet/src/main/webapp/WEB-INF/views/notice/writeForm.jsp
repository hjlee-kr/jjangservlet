<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 등록 폼</title>

	<!-- datepicker -->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.0/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://code.jquery.com/ui/1.14.0/jquery-ui.js"></script>

	
<!-- 4. 우리가 만든 라이브러리 등록 -->
<script type="text/javascript" src="boardInputUtil.js"></script>

<!-- 2. 라이브러리 등록확인 -->
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
	
	
	
	//5. 이벤트 처리
	$("#writeForm").submit(function() {
		console.log("writeForm submit event......");
		
		//6. 필수항목체크 (제목, 내용)
		// isEmpty(객체, 항목이름, 트림유무)
		if (isEmpty("#title", "제목", 1)) return false;
		if (isEmpty("#content", "내용", 1)) return false;
		
		//7. 길이체크
		// lengthCheck(객체, 항목이름, 최소, 최대, 트림유무)
		if (lengthCheck("#title", "제목", 3, 100, 1)) return false;
		if (lengthCheck("#content", "내용", 3, 600, 1)) return false;
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
  <h2><i class="fa fa-edit"></i> 공지사항 글 등록 폼</h2>
  <form action="write.do" method="post" id="writeForm">
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
      <label for="startDate">게시일</label>
      <input type="text" class="form-control datepicker" id="startDate"
        name="startDate">
    </div>
    <div class="form-group">
      <label for="endDate">종료일</label>
      <input type="text" class="form-control datepicker" id="endDate"
        name="endDate">
    </div>
    <button type="submit" class="btn btn-primary">등록</button>
    <button type="reset" class="btn btn-secondary">새로입력</button>
    <button type="button" class="btn btn-success"
    	id="cencelBtn">취소</button>
  </form>
</div>
</body>
</html>



