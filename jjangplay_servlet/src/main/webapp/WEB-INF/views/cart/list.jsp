<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!-- 데이터는 DispatcherServlet에 담겨있다(request) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 리스트</title>
<script type="text/javascript" src="boardInputUtil.js"></script>

<style type="text/css">
.imageDiv {
	background: black;
}
</style>
<!-- 4. 우리가 만든 라이브러리 등록 -->

<script type="text/javascript">
$(function(){
	//  2. 라이브러리 등록확인 
	console.log("jquery loading......");
	
	// 제목과 내용부분의 제일 높은 것을 기준으로 모두 맞추기
	let titleHeights = [];
	
	$(".title").each(function(idx, title){
		console.log($(title).height());
		titleHeights.push($(title).height());
	});
	
	let maxTitleHeight = Math.max.apply(null, titleHeights);
	console.log("maxTitleHeight=" + maxTitleHeight);
	
	$(".title").height(maxTitleHeight);
	
	// 첫번째 이미지로 가로세로 구하기 (5:4)
	let imgWidth = $(".imageDiv:first").width();
	let imgHeight = $(".imageDiv:first").height();
	console.log("image width=" + imgWidth + ",height=" + imgHeight);
	// 가로size는 동일하다. 
	// 가로size를 기준으로 세로size를 정해준다.
	let height = imgWidth / 5 * 4;
	console.log("height=",height);
	// 전체 imageDiv 높이를 조정
	$(".imageDiv").height(height);
//	$(".imageDiv > img").height(height);
	$(".imageDiv > img").each(function(idx, image) {
	//	alert(image);
		if($(image).height() > height) {
			let image_width = $(image).width();
			let image_height = $(image).height();
			let width = height / image_height * image_width;
			console.log("image_width=" + image_width);
			console.log("image_height=" + image_height);
			console.log("width=" + width);
			console.log("height=" + height);
			//이미지 높이를 줄이고
			$(image).height(height);
			//이미지 너비를 줄입니다.
			$(image).width(width);
		}
		
	});
	
	// 이벤트 처리
	$(".dataRow").click(function() {
		let gno = $(this).find(".gno").text();
		console.log("gno=" + gno);
		location="view.do?gno=" + gno + "&${pageObject.pageQuery}";
	});
	
	
	
	
	$("#perPageNum").change(function(){
		$("#searchForm").submit();
	});
	
	// 검색데이터 세팅
	$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
	$("#perPageNum")
		.val("${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");
	
});
</script>

</head>
<body>

<div class="container p-3 my-3">
	<h1><i class="fa fa-align-justify"></i> 장바구니 리스트</h1>

	<c:if test="${empty list }">
		<h4>장바구니가 비었습니다.</h4>
	</c:if>
	<c:if test="${!empty list }">
		<div class="row">
			<div class="col-sm-2">상품번호</div>
			<div class="col-sm-2">상품사진</div>
			<div class="col-sm-2">상품이름</div>
			<div class="col-sm-2">상품가격</div>
			<div class="col-sm-2">수량</div>
			<div class="col-sm-2">배송료</div>
		</div>
		<c:forEach items="${list }" var="vo" varStatus="vs">
			<div class="row">
				<div class="col-sm-2">${vo.gno }</div>
				<div class="col-sm-2">
					<img src="${vo.imageName }" style="width:20px; height:20px" alt="이미지">
				</div>
				<div class="col-sm-2">${vo.name }</div>
				<div class="col-sm-2">${vo.discount }</div>
				<div class="col-sm-2">${vo.count }</div>
				<div class="col-sm-2">${vo.delivery_cost }</div>
			</div>
		</c:forEach>
	</c:if><!-- 데이터 존재했을때 처리 끝 -->
</div>
</body>
</html>