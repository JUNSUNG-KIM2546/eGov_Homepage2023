<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>CRUDTEST 등록/수정 페이지</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
<style>
table{border-collapse: collapse;}
th{font-weight: bold;}
th, td{padding: 5px; border: 1px solid #000;}
a{text-decoration:underline; margin:5px}
</style>
</head>
<body>

<c:choose>
	<c:when test="${not empty result.testId}">
		<c:set var="actionUrl" value="/test/update.do"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="/test/insert.do"/>
	</c:otherwise>
</c:choose>

* 등록폼
<form action="${actionUrl}" method="post" name="testVO">
	<input type="hidden" name="testId" value="${result.testId}"/>
	<label for="sj">제목		</label>
	<input type="text" name="sj" value="${result.sj}" placeholder="제목을 입력하세요."/>
	<br/>
	<label for="userNm">작성자	</label>
	<input type="text" name="userNm" value="<c:out value="${result.userNm}" default="${USER_INFO.name}"/>" placeholder="작성자를 입력하세요." />
	<br/>
	<label for="cn">내용		</label>
	<textarea id="cn" name="cn" placeholder="내용를 입력하세요."><c:out value="${result.cn}"/></textarea>
	<br/>
	<c:choose>
		<c:when test="${not empty result.testId}">
			<button type="submit">수정</button>
		</c:when>
		<c:otherwise>
			<button type="submit">등록</button>
		</c:otherwise>
	</c:choose>
	<a href="/test/select.do?testId=${result.testId}">취소</a>
	
</form>


</body>
</html>
