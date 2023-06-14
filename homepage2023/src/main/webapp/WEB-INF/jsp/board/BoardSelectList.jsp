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
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>BOARD 기본게시판</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
</head>
<body>

<%-- BBS Style --%>
<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
<%-- 공통 Style --%>
<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />

<%-- 게시판 타입 --%>
<c:set var="boardType">
	<c:choose>
		<c:when test="${not empty searchVO.boardType}">
			<c:out value="${searchVO.boardType}"></c:out>
		</c:when>
		<c:otherwise>
			NORMAL
		</c:otherwise>
	</c:choose>
</c:set>

<%-- 기본 URL --%>
<c:url var ="_BASE_PARAM" value="">
	<c:param name="boardType" value="${boardType}"/>
	<c:if test="${not empty searchVO.searchCondition}"><c:param name="searchCondition" value="searchVO.searchCondition" /></c:if>
	<c:if test="${not empty searchVO.searchKeyword}"><c:param name="searchKeyword" value="searchVO.searchKeyword" /></c:if>
</c:url>

접속계정 : <c:out value="${USER_INFO.id}"/>

<div class="container">
	<div id="contents">
		<%-- 검색 영역 (검색이나 데이터를 보낼때 폼테그 안에서)--%>
		<div id="bbs_search">
			<form name="frm" method="post" action="/board/selectList.do">
				<fieldset>
					<legend>검색조건입력폼</legend>
					<input type="hidden" name="boardType" value="${boardType}"/>
					<label for="ftext" class="hdn">검색분류선택</label>
					<select name="searchCondition" id="ftext">
						<option value="0" <c:if test="${searchVO.searchCondition eq '0'}">selected="selected"</c:if>>제목</option>
						<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>내용</option>
						<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>작성자</option>
					</select>
					<label for="inp_text" class="hdn">검색어입력</label>
					<input name="searchKeyword" value="<c:out value="${searchVO.searchKeyword}"/>" type="text" class="inp_s" id="inp_text"/>
					<span class="bbtn_s"><input type="submit" value="검색" title="검색(수업용 게시판 게시물 내)" /></span>
				</fieldset>
			</form>
		</div>
		
		<%-- 목록 영역 --%>
		<div id="bbs_wrap">
			<div class="total">
				총 게시물
				<strong><c:out value="${paginationInfo.totalRecordCount}"/></strong>건 |
				현재페이지 <strong><c:out value="${paginationInfo.currentPageNo}"/></strong>/
				<c:out value="${paginationInfo.totalPageCount}"/>
			</div>
			<c:choose>
				<c:when test="${searchVO.boardType eq 'IMAGE'}">
					<div class="list_photo">
						<ul>
							<c:forEach var="result" items="${resultList}" varStatus="status">
								<c:url var="viewUrl" value="/board/select.do${_BASE_PARAM}">
									<c:param name="boardId" 	value="${result.boardId}"/>
									<c:param name="pageIndex" 	value="${searchVO.pageIndex}"/>]
								</c:url>
								<li>
									<div class="ph_img">
										<a href="${viewUrl}">
											<c:choose>
												<c:when test="${empty result.atchFileNm}">
													<img alt="<c:out value="${result.boardSj}"/>" src="/asset/BBSTMP_0000000000001/images/noimg.gif" width="200" height="140"/>
												</c:when>
												<c:otherwise>
													<c:url var="thumbUrl" value="/cmm/fms/getThumbImage.do">
														<c:param name="thumbYn" 	value="Y"/>
														<c:param name="atchFileNm" 	value="${result.atchFileNm}"/>
													</c:url>
													<img alt="${result.boardSj}" src="${thumbUrl}"/>
												</c:otherwise>
											</c:choose>
										</a>
									</div>
									<div class="ph_cont">
										<span>
											<a href="${viewUrl}">
												<c:if test="${result.othbcAt eq 'Y'}">
													<img alt="비밀 글 아이콘" src="/asset/BBSTMP_0000000000001/images/ico_board_lock.gif">
												</c:if>
												<c:choose>
													<c:when test="${fn:length(result.boardSj) > 15}">
														<c:out value="${fn:substring(result.boardSj, 0, 15)}"/>...
													</c:when>
													<c:otherwise>
														<c:out value="${result.boardSj}"/>
													</c:otherwise>
												</c:choose>
											</a>
										</span>
										<span class="ph_date"><fmt:formatDate value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd"/></span>
									</div>
								</li>
							</c:forEach>
						</ul>
						<%-- 게시글이 없을 경우 --%>
						<c:if test="${fn:length(resultList) == 0}">
							<table class="boardscn_list"><tr><td><spring:message code="common.nodata.msg"/></td></tr></table>
						</c:if>
					</div>
				</c:when>
				
				<c:otherwise>
					<div class="bss_list">
						<table class="list_table">
							<thead>
								<tr>
									<th class="num" 	scope="col">번호</th>
									<th class="tit" 	scope="col">제목</th>
									<th class="writer" 	scope="col">작성자</th>
									<th class="date" 	scope="col">작성일</th>
									<th class="hits" 	scope="col">조회수</th>
								</tr>
							</thead>
							<tbody>
							
							<%-- 공지 글 --%>
							<c:forEach var="result" items="${noticeResultList}" varStatus="status">
								<tr class="notice">
									<td class="num"><span class="label-bbs spot">공지</span></td>
									<td class="tit">
										<c:url var="viewUrl" value="/board/select.do${_BASE_PARAM}">
											<c:param name="boardId"		value="${result.boardId}"/>
											<c:param name="pageIndex" 	value="${searchVO.pageIndex}"/>
										</c:url>
										<a href="${viewUrl}"><c:out value="${result.boardSj}"/></a>
									</td>
									<td class="writer"	data-cell-header="작성자: "><c:out value="${result.frstRegisterId}"/></td>
									<td class="date" 	data-cell-header="작성일: "><fmt:formatDate value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd"/></td>
									<td class="hits"	data-cell-header="조회수: "><c:out value="${result.inqireCo}"/></td>
								</tr>
							</c:forEach>
							
							<%-- 일반 글 --%>
							<c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<td class="num"><c:out value="${paginationInfo.totalRecordCount - ((searchVO.pageIndex-1) * searchVO.pageUnit) - (status.count - 1)}"/></td>
									<td class="tit">
										<c:url var="viewUrl" value="/board/select.do${_BASE_PARAM}">
											<c:param name="boardId" 	value="${result.boardId}"/>
											<c:param name="pageIndex" 	value="${searchVO.pageIndex}"/>
										</c:url>
										<a href="${viewUrl}">
											<c:if test="${result.othbcAt eq 'Y'}">
												<img src="/asset/BBSTMP_0000000000001/images/ico_board_lock.gif" alt="비밀 글 아이콘" />
											</c:if>
											<c:out value="${result.boardSj}"/>
										</a>
									</td>
									<td class="writer" 	data-cell-header="작성자 : "><c:out value="${result.frstRegisterId}"/></td>
									<td class="date" 	data-cell-header="작성일 : "><fmt:formatDate value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd"/></td>
									<td class="hits" 	data-cell-header="조회수 : "><c:out value="${result.inqireCo}"/></td>
								</tr>
							</c:forEach>
							
							<%-- 게시글이 없을 경우 --%>
							<c:if test="${fn:length(resultList) == 0}">
								<tr class="empty"><td colspan="5">검색 데이터가 없습니다.</td></tr>
							</c:if>
							</tbody>
						</table>
					</div>
				</c:otherwise>
			</c:choose>
			
			<div id="paging">
				<c:url var="pageUrl" value="/board/selectList.do${_BASE_PARAM}"/>
				<c:set var="pagingParam"><c:out value="${pageUrl}"/></c:set>
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="${pagingParam}"/>
			</div>		
		</div>
		<div class="btn-cont ar">
		<c:url var="regUrl" value="/board/boardRegist.do${_BASE_PARAM}"/>
			<a href="${regUrl}" class="btn spot"><i class="ico-check-spot"></i>글쓰기</a>
		</div>
	</div>
</div>
<script>
<c:if test="${not empty message}">
	alert("${message}");
</c:if>
</script>
	
</body>
</html>
