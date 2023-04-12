<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>데이터 가져오기~</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
</head>
<body>
	<c:choose>	<%-- if문 같은거 --%>
	    <c:when test="${not empty result.tempId}">
	        <c:set var="actionUrl" value="/temp/update.do"/>	<%-- 변수 선언 --%>
	    </c:when>
	    <c:otherwise>
	        <c:set var="actionUrl" value="/temp/insert.do"/>
	    </c:otherwise>
	</c:choose>

	<%--  <form action="/temp/insert.do" method="post" name="tempVO"> --%>
	<form action="${actionUrl}" method="post" name="tempVO">
		<input type="hidden" name="tempId" value="${result.tempId}"/>
        <label for="tempVal">값 정보 : </label>
        <input type="text" id="tempVal" name="tempVal" value="${result.tempVal}"/>
        <br/>
        <c:choose>
        	<c:when test="${not empty result.tempId}">
        		<button type="submit">수정</button>
        	</c:when>
        	<c:otherwise>
        		<button type="submit">등록</button>
       		 </c:otherwise>
        </c:choose>
    </form>
</body>
</html>

<%-- 이거슨 JSP 주석 --%>
