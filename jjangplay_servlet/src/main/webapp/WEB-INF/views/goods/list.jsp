<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!-- 데이터는 DispatcherServlet에 담겨있다(request) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리스트</title>
<script type="text/javascript">
$(function() {

	$(".dataRow").click(function() {
		let gno = $(this).find(".gno").text();
		console.log("gno = " + gno);
		location="view.do?gno=" + gno;
	});
});
</script>

</head>
<body>
<div class="container p-3 my-3">
	<h1><i class="fa fa-align-justify"></i> 상품 리스트</h1>

  <table class="table table-hover">
		<!-- 데이터의 제목 줄 표시 -->
		<tr>
			<!-- th : table head - 테이블의 제목줄에 사용 -->
			<!-- 리스트 : 상품번호, 상품명, 모델NO, 제조사 -->
			<th>상품번호</th>
			<th>상품명</th>
			<th>모델No</th>
			<th>제조사</th>
		</tr>
		<!-- 실제 데이터 : 데이터가 있는 만큼 <tr></tr> -->
		<c:if test="${empty list }">
			<tr>
				<td colspan="4">
					등록된 상품이 없습니다.
				<td>
			</tr>
		</c:if>
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="vo">
				<tr class="dataRow">
					<td class="gno">${vo.gno}</td>
					<td>${vo.name}</td>
					<td>${vo.modelNo}</td>
					<td>${vo.company}</td>
				</tr>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="4">
				<a href="writeForm.do?perPageNum=${pageObject.perPageNum }"
					class="btn btn-primary">상품등록</a>
			</td>
		</tr>
  </table>
	<div>
		<pageNav:pageNav listURI="list.do" pageObject="${pageObject}"></pageNav:pageNav>
	</div>

</div>
</body>
</html>