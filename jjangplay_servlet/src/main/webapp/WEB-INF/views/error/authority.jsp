<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한오류</title>
</head>
<body>
<div class="container">
	<div class="card">
	  <div class="card-header">
	  	<h3>권한오류</h3>
	  </div>
	  <div class="card-body" id="errorDiv">
	  	<div class="row">
			  <div class="col-md-3">요청 URI</div>
			  <div class="col-md-9">${uri }</div>
			</div>
	  	<div class="row">
			  <div class="col-md-3">오류 메시지</div>
			  <div class="col-md-9">요청하신 페이지에 접근할 권한이 없습니다.</div>
			</div>
	  	<div class="row">
			  <div class="col-md-3">조치 사항</div>
			  <div class="col-md-9">
			  로그인 정보의 등급을 확인해 주세요. 오류가 있는 경우 관리자에게 문의하세요.
			  </div>
			</div>
		</div>
	  <div class="card-footer">
	  	<a href="/main/main.do" class="btn btn-primary">메인으로가기</a>
	  </div>
	</div>
</div>
</body>
</html>




