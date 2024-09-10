<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>
	<!-- datepicker -->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.14.0/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://code.jquery.com/ui/1.14.0/jquery-ui.js"></script>

<!-- 2. 라이브러리 등록확인 -->
<script type="text/javascript">
//페이지가 로딩후 세팅한다.
//$(document).ready(function(){~~});
$(function() {
	console.log("jquery loading......");
	
	let now = new Date();
	let startYear = now.getFullYear();
	let yearRange = (startYear - 100) + ":" + (startYear);
	
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
<!-- 정규표현식 -->
<!-- 시작:^,끝:$ -->
<!-- []:한글자의 패턴 -->
<!-- ^가 앞에있으면 사용불가표시 -->
<!-- .은 엔터를 제외한 모든문자 -->
<!-- {최소,최대}:앞에쓰여진 패턴이 적용되는 최소와 최대 -->
<!-- 알파벳과한글사용가능한패턴이 3,20자 => ^[a-z가-힣]{3,20}$ -->
<!-- 공백을 제외한 모든가 가능한 3,20자 => ^[^ .]{3,20}$ -->
<div class="container">
	<form action="/member/write.do" method="post" enctype="multipart/form-data">
	  <div class="form-group">
	    <label for="id">아이디</label>
	    <input type="text" class="form-control" maxlength="20"
	    	pattern="^[a-zA-Z][a-zA-Z0-9]{2,19}$" required
	    	placeholder="맨앞글자는 영문자 뒤에는 영문자 또는 숫자 입력, 3~20자"
	    	id="id" name="id">
	  </div>
	  <div id="checkIdDiv" class="alert alert-danger">
	    아이디는 필수 입력입니다. 3글자에서 20자까지 사용합니다.
	  </div>
	  <div class="form-group">
	    <label for="pw">비밀번호</label>
	    <input type="password" class="form-control"
	    	maxlength="20" required
	    	pattern="^.{3,20}$"
	    	placeholder="비밀번호 입력" id="pw" name="pw">
	  </div>
	  <div id="pwDiv" class="alert alert-danger">
	    비밀번호는 필수 입력입니다. 3글자에서 20자까지 사용합니다.
	  </div>
	  <div class="form-group">
	    <label for="pw2">비밀번호확인</label>
	    <input type="password" class="form-control"
	    	maxlength="20" required
	    	pattern="^.{3,20}$"
	    	placeholder="비밀번호 입력" id="pw2">
	  </div>
	  <div id="pw2Div" class="alert alert-danger">
	    비밀번호확인은 필수 입력입니다. 3글자에서 20자까지 사용합니다.
	  </div>
	  <div class="form-group">
	    <label for="name">이름</label>
	    <input type="text" class="form-control" maxlength="10"
	    	pattern="^[a-zA-Z가-힝]{2,10}$" required
	    	placeholder="이름입력"
	    	title="한글 또는 영문으로 2자이상 10자 이내"
	    	id="name" name="name">
	  </div>
	  <div class="form-group">
	  	<label>성별</label>
	  	<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input"
			     name="gender" value="남자" checked>남자
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input"
			     name="gender" value="여자">여자
			  </label>
			</div>
	  </div>
	  <div class="form-group">
	    <label for="birth">생년월일</label>
	    <input type="text" class="form-control datepicker" required
	    	id="birth" name="birth">
	  </div>
	  <div class="form-group">
	    <label for="tel">연락처</label>
	    <input type="text" class="form-control"
	    	placeholder="xxx-xxxx-xxxx"
	    	id="tel" name="tel">
	  </div>
	  <div class="form-group">
	    <label for="email">이메일</label>
	    <input type="email" class="form-control" required
	    	placeholder="id@도메인"
	    	id="email" name="email">
	  </div>
	  <div class="form-group">
	    <label for="photo">사진</label>
	    <input type="file" class="form-control"
	    	id="photo" name="photo">
	  </div>
	  <button type="submit" class="btn btn-primary">가입</button>
	  <button type="reset" class="btn btn-secondary">다시입력</button>
	  <button type="button" onclick="history.back()" class="btn btn-warning">취소</button>
	</form>
</div>
</body>
</html>






