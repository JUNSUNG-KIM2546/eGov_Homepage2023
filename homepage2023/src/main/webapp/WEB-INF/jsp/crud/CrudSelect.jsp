<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>CRUD 상세 목록</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
<style>
table{border-collapse: collapse;}
th{font-weight: bold;}
th, td{padding: 5px; border: 1px solid #000;}
a{text-decoration:underline; margin:5px}
</style>
</head>
<body>
<table>
	<tbody>
		<tr>
			<th>제목</th>
			<td><c:out value="${result.crudSj}"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><c:out value="${result.userNm}"/></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><fmt:formatDate value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><c:out value="${result.crudCn}"/></td>
		</tr>
	</tbody>
</table>

<div class="box-btn">
	<c:url var="uptUrl" value="/crud/crudRegist.do">
		<c:param name="crudId" value="${result.crudId}"/>
	</c:url>
	<a href="${uptUrl}">수정</a>
	
	<c:url var="delUrl" value="/crud/delete.do">
		<c:param name="crudId" value="${result.crudId}"/>
	</c:url>
	<a href="${delUrl}" class="btn-del">삭제</a>
	
	<a href="/crud/selectList.do">목록</a>
</div>
<script>
$(document) .ready(function() {
	$(".btn-del") .click(function() {
		if(!confirm("삭제하시겠습니까?")) {
			return false;
		}
	});
});
</script>

</body>
</html>
