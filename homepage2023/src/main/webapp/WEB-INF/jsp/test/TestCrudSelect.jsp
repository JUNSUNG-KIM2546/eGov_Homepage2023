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
<title>CRUDTEST 상세 목록</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
<style>
table{border-collapse: collapse;}
th{font-weight: bold;}
th, td{padding: 5px; border: 1px solid #000;}
a{text-decoration:underline; margin:5px}
</style>
</head>
<body>

접속계정 : <c:out value="${USER_INFO.id}"/>

<table>
	<tbody>
		<tr>
			<th>제목</th>
			<td><c:out value="${result.sj}"/></td>
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
			<th>수정일</th>
			<td><fmt:formatDate value="${result.lastUpdtPnttm}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><c:out value="${result.cn}"/></td>
		</tr>
	</tbody>
</table>

<div class="box-btn">
	<c:url var="uptUrl" value="/test/testRegist.do">
		<c:param name="testId" value="${result.testId}"/>
	</c:url>
	<a href="${uptUrl}"><button>수정</button></a>
	
	<c:url var="delUrl" value="/test/delete.do">
		<c:param name="testId" value="${result.testId}"/>
	</c:url>
	<a href="${delUrl}" class="btn-del"><button>삭제</button></a>
	
	<a href="/test/selectList.do"><button>목록</button></a>
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
