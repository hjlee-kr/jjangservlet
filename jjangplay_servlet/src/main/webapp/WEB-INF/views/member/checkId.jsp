<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty id }">
	사용가능한 아이디 입니다.
</c:if>
<c:if test="${!empty id }">
	중복된 아이디 입니다. 다른 아이디를 입력하세요.
</c:if>