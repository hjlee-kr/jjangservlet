<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>

<h4>공지사항 리스트</h4>

<table class="table">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>게시종료일</th>
	</tr>
	<c:forEach items="${noticeList }" var="vo">
		<tr class="dataRow board boardLink">
			<td class="no">${vo.no }</td>
			<td>${vo.title }</td>
			<td>${vo.endDate }</td>
		</tr>
	</c:forEach>
</table>