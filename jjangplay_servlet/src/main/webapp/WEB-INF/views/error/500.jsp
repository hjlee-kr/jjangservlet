<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 Error</title>
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




