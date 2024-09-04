<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 수정 폼</title>

	<!-- datepicker -->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.0/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://code.jquery.com/ui/1.14.0/jquery-ui.js"></script>

	
<!-- 4. 우리가 만든 라이브러리 등록 -->
<script type="text/javascript" src="boardInputUtil.js"></script>

<!-- 2.라이브러리 확인 -->
<script type="text/javascript">
$(function(){
	console.log("jquery loading.........");
	
	// 날짜입력 -datepicker 시작
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
	// 날짜입력 -datepicker 끝
	
	// 5.이벤트 실행
	$("#updateForm").submit(function(){
		console.log("updateForm event......");
		
		// 6.필수항목체크 (제목, 내용, 작성자, 비밀번호확인)
		// isEmpty(객체, 항목, 트림유무)
		if (isEmpty("#title", "제목", 1)) return false;
		if (isEmpty("#content", "내용", 1)) return false;
		
		// 7.길이체크 (제목, 내용, 작성자, 비밀번호확인)
		// lengthCheck(객체, 항목, 최소, 최대, 트림유무)
		if (lengthCheck("#title", "제목", 3, 100, 1)) return false;
		if (lengthCheck("#content", "내용", 3, 100, 1)) return false;
		
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
  <h2><i class="fa fa-pencil-square-o"></i> 공지사항 글 수정 폼</h2>
  <form action="update.do" method="post" id="updateForm">
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
      <label for="startDate">게시일</label>
      <input type="text" class="form-control datepicker" id="startDate"
        name="startDate" value="${ vo.startDate }">
    </div>
    <div class="form-group">
      <label for="endDate">종료일</label>
      <input type="text" class="form-control datepicker" id="endDate"
        name="endDate" value="${ vo.endDate }">
    </div>
    <button type="submit" class="btn btn-primary">수정</button>
    <button type="reset" class="btn btn-secondary">다시쓰기</button>
    <button type="button" class="btn btn-success"
    	id="cancelBtn">취소</button>
  </form>
</div>
</body>
</html>