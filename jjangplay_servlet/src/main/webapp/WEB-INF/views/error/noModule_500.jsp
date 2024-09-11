<!-- isErrorPage="true" : 현재 jsp가 에러 처리 페이지로 지정
		excetion이 파라메터로 넘어옵니다.
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	// 넘어온 예외객체인 exception을 request에 e속성으로 담아서 처리한다.
	request.setAttribute("e", exception);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 Error</title>
  <!-- Bootstrap 4 + jquery 라이브러리 등록 - CDN 방식 -->
  <!-- 여기에 사용할 라이브러리들을 한번에 적용할 수 있습니다. -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

	
	<!-- awesome icon 라이브러리 등록 (CDN) -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- google icon 라이브러리 등록 (CDN) -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<style type="text/css">
	#errorDiv>.row{
		padding: 10px;
		border-top: 1px dotted #ccc;
		margin: 0 10px;
	}
</style>
</head>
<body>
<div class="container">
	<div class="card">
	  <div class="card-header">
	  	<h3><i class="fa fa-times-circle"></i> 프로세스 처리 오류 (500)</h3>
	  </div>
	  <div class="card-body" id="errorDiv">
	  	<div class="text-center">
	  		<i class="material-icons" style="font-size:100px;color:red">error</i>
	  	</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 오류 객체</div>
			  <div class="col-md-9">${e.getClass().simpleName }</div>
			</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 오류 메시지</div>
			  <div class="col-md-9">${e.message }</div>
			</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 조치 사항</div>
			  <div class="col-md-9">
			  오류로 인해 불편을 드려 죄송합니다.<br>
			  다시 한 번 시도해 주세요<br>
			  오류가 계속 발생하면 전산 담당자에게 연락주세요.
			  </div>
			</div>
		</div>
	  <div class="card-footer">
	  	<span class="float-right">
	  		<a href="/main/main.do" class="btn btn-primary">메인으로가기</a>
	  	</span>
	  </div>
	</div>
</div>
</body>
</html>




