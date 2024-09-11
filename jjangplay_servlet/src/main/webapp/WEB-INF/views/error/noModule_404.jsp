<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 Error</title>
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
	  	<h3><i class="fa fa-times-circle"></i> 요청 자원 오류 (404)</h3>
	  </div>
	  <div class="card-body" id="errorDiv">
	  	<div class="text-center">
	  		<i class="material-icons" style="font-size:100px;color:red">error</i>
	  	</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 요청 URI</div>
			  <div class="col-md-9">${uri }</div>
			</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 오류 메시지</div>
			  <div class="col-md-9">요청하신 페이지의 주소가 존재하지 않습니다.</div>
			</div>
	  	<div class="row">
			  <div class="col-md-3"><i class="fa fa-check"></i> 조치 사항</div>
			  <div class="col-md-9">
			  요청하신 페이지의 주소를 확인하시고 다시 시도해 주세요.
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