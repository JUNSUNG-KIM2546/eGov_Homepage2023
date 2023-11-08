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
<title>예약목록</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
	
	<%-- BBS Style --%>
	<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
	<%-- 공통 Style --%>
	<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />
	
	<%-- 기본 URL --%>
	<c:url var="_BASE_PARAM" value="">
		<c:param name="searchCondition" value="${searchVO.searchCondition}"></c:param>
		<c:if test="${not empty searchVO.searchKeyword }"><c:param name="searchKeyword" value="${searchVO.searchKeyword}"/></c:if>
	</c:url>
	
	<!-- content 시작 -->
	<div id="content">
		<div class="container">
			<div id="contents">
				<!-- 검색영역 -->
				<div id="bbs_search">
					<form action="/rsv/selectList.do" name="frm" method="post">
						<fieldset>
							<legend>검색조건입력폼</legend>
							<label for="ftext" class="hdn">검색분류선택</label>
							<select name="searchCondition" id="ftext">
								<option value="0"><c:if test="${searchVO.searchCondition eq '0'}">selected="selected"</c:if>프로그램명</option>
								<option value="1"><c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>내용</option>
							</select>
							<label for="inp_text" class="hdn">검색어입력</label>
							<input name="searchKeyword" value="<c:out value="${searchVO.searchKeyword}"/>" type="text" class="inp_s" id="inp_text">
							<span class="bbtn_s"><input type="submit" value="검색" title="검색 (수업용 예약관리 게시물 내)"/></span>
						</fieldset>
					</form>
				</div>
				
				<!-- 목록영역 -->
				<div id="bbs_wrap">
					<div class="total">
						총 게시물
						<strong><c:out value="${paginationInfo.totalRecordCount}"/></strong>건 |
						현재페이지 <strong><c:out value="${paginationInfo.currentPageNo}"/></strong>/
						<c:out value="${paginationInfo.totalPageCount}"/>
					</div>
					
					<div class="bss_list">
						<table class="list_table">
							<thead>
								<tr>
									<th class="num" 	scope="col">번호</th>
									<th class="tit" 	scope="col">프로그램명</th>
									<th scope="col">신청유형</th>
									<th scope="col">신청기간</th>
									<th scope="col">운영일</th>
									<th scope="col">운영시간</th>
									<th scope="col">강사명</th>
									<th scope="col">상태</th>
								</tr>
							</thead>
							
							<tbody>
								<%-- 일반 글 --%>
								<c:forEach var="result" items="${resultList}" varStatus="status">
									<tr>
										<td class="num"><c:out value="${paginationInfo.totalRecordCount - ((searchVO.pageIndex-1) * searchVO.pageUnit) - (status.count - 1)}"/></td>
										<td class="tit">
											<c:url var="viewUrl" value="/rsv/rsvSelect.do${_BASE_PARAM}">
												<c:param name="resveId" 	value="${result.resveId}"/>
												<c:param name="pageIndex" 	value="${searchVO.pageIndex}"/>
											</c:url>
											<a href="${viewUrl}">
												<c:out value="${result.resveSj}"/>
											</a>
										</td>
										<td>
											<c:choose>
												<c:when test="${result.resveSeCode eq 'TYPE01'}">선착순</c:when>
												<c:when test="${result.resveSeCode eq 'TYPE02'}">승인관리</c:when>
											<c:otherwise>-</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:out value="${result.reqstBgnde}"/> ~
											<c:out value="${result.reqstEndde}"/>
										</td>
										<td>
											<c:out value="${result.useBeginDt}"/> ~
											<c:out value="${result.useEndDt}"/>
										</td>
										<td><c:out value="${result.useBeginTime}~${result.useEndTime}"/></td>
										<td><c:out value="${result.recNm}"/></td>
										<td>
											<c:choose>
												<c:when test="${result.applyStatus eq '1' }">접수 대기중</c:when>
												<c:when test="${result.applyStatus eq '2' }">접수중</c:when>
												<c:when test="${result.applyStatus eq '3' }">접수마감</c:when>
												<c:when test="${result.applyStatus eq '4' }">운영중</c:when>
												<c:otherwise>종료</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
								
								<%-- 게시글이 없을 경우 --%>
								<c:if test="${fn:length(resultList) == 0}">
									<tr class="empty"><td colspan="9">검색 데이터가 없습니다.</td></tr>
								</c:if>
							</tbody>
						</table>
					</div>	
					
					<div id="paging">
						<c:url var="pageUrl" value="/rsv/SelectList.do${_BASE_PARAM}"/>
						<c:set var="pagingParam"><c:out value="${pageUrl}"/></c:set>
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="${pagingParam}"/>
					</div>		
					
					<c:if test="${not empty USER_INFO.id}">
						<div class="btn-cont ar">
							<a href="/rsv/selectApplyList.do" class="btn spot">신청내역 확인</a>
						</div>
					</c:if>
					
				</div>
			</div>
		</div>
	</div>
	<!-- contents 끝 -->
	
	<script>
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
	</script>

</body>
</html>