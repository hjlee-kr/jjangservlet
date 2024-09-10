<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>

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

	
	

});
</script>

<h4>이미지게시판 리스트</h4>


<c:if test="${empty imageList }">
	<h4>데이터가 존재하지 않습니다.</h4>
</c:if>
<c:if test="${!empty imageList }">
	<div class="row">
			<!-- 이미지 list의 데이터가 있는 만큼 표시하는 처리 시작 -->
			<c:forEach items="${imageList }" var="vo" varStatus="vs">
				<!-- 줄바꿈처리 - 3개를 표시하면 줄을 바꾼다. -->
				<c:if test="${(vs.index != 0) && (vs.index%3 == 0) }">
					${"</div>"}
					${"<div class='row'>"}
				</c:if>
				<!-- 데이터 표시 시작 -->
				<div class="col-md-4 dataRow">
					<div class="card" style="width:100%">
						<div class="imageDiv align-content-center text-center">
					  	<img class="card-img-top" src="${vo.fileName }" alt="Card image">
					  </div>
					  <div class="card-body title">
					    <h4 class="card-title">
					    	<span class="float-right">${vo.writeDate }</span>
					    	${vo.name }(${vo.id })
					    </h4>
					    <p class="card-text">
					    	<span class="no">${vo.no }</span>.${vo.title }
							</p>
					  </div>
					</div><!-- end of card -->
				</div>
				<!-- 데이터 표시 끝 -->
			</c:forEach>
			<!-- 이미지 데이터 반복 표시 끝 -->
		</div>
</c:if>