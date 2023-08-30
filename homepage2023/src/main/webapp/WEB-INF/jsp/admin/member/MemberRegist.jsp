<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Language" content="ko">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>(관리자) 회원 수정</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
</head>
<body>

	<%-- BBS Style --%>
	<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
	<%-- 공통 Style --%>
	<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />
	
	<%-- 기본 URL --%>
	<c:url var="_BASE-PARAM" value="">
		<c:param name="searchCondition" value="${searchVO.searchCondition}"></c:param>
		<c:param name="searchKeyword" value="${searchVO.searchKeyword}"></c:param>
	</c:url>
	
	<div class="container">
		<div id="contents">
			<table class="chart2">
				<caption> 게시판 작성 </caption>
				<colgroup>
					<col style="width:120px"/>
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"> 고유번호 </th>
						<td><c:out value="${result.esntlId}"/></td>
					</tr>
					<tr>
						<th scope="row"> 회원ID </th>
						<td><c:out value="${result.emplyrId}"/></td>
					</tr>
					<tr>
						<th scope="row"> 회원명 </th>
						<td><c:out value="${result.userNm}"/></td>
					</tr>
					<tr>
						<th scope="row"> 비밀번호 </th>
						<td><button type="button" id="btn-pw" class="btn spot">비밀번호 재발급</button></td>
					</tr>
					<tr>
						<th scope="row"> 이메일 주소 </th>
						<td><c:out value="${result.emailAdres}"/></td>
					</tr>
					<tr>
						<th scope="row"> 가입일 </th>
						<td><fmt:formatDate value="${result.sbscrbDe}" pattern="yyyy-MM-dd" /> </td>
					</tr>
				</tbody>
			</table>
			
			<div class="btn-cont ac">
				<c:url var="listUrl" value="/admin/member/memberList.do${_BASE_PARAM}" />
				<a href="${listUrl}" class="btn"> 목록 </a>
			</div>
		</div>
	</div>
	
	<script>
	/* 비밀번호 재발급 */
	$("#btn-pw").click(function() {
		$.ajax({
			url : "/admin/member/changePwRandom.do",
			type : "post",
			data : {"emplyrId": "${result.emplyrId}", "esntlId": "${result.esntlId}", "emailAdres": "${result.emailAdres}"},
			dataType : "json",
			success : function(data) {
				if(data.successYn == "Y") {
					alert("비밀번호 재발급이 완료되었습니다.");
				} else {
					alert(data.message);
				}
			}, error : function() {
				alert("error");
			}
		});
		
		return false;
	});
	</script>

</body>
</html>
