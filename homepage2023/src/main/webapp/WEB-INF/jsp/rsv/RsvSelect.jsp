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
<title>한국폴리텍 예약관리 시스템</title>
	
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
	
	<!-- jQuery UI -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">

</head>
<body>

	<%-- BBS Style --%>
	<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
	<%-- 공통 Style --%>
	<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />
	
	<c:choose>
		<c:when test="${not empty searchVO.resveId}">
			<c:set var="actionUrl" value="/rsv/rsvUpdate.do"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="actionUrl" value="/rsv/rsvInsert.do"></c:set>
		</c:otherwise>
	</c:choose>
	
	<%-- 기본 URL --%>
	<c:url var="_BASE_PARAM" value="">
		<c:param name="resveId" value="${searchVO.resveId}"></c:param>
		<c:param name="pageIndex" value="${searchVO.pageIndex}"></c:param>
		<c:if test="${not empty searchVO.searchCondition }"><c:param name="searchCondition" value="${searchVO.searchCondition}"/></c:if>
		<c:if test="${not empty searchVO.searchKeyword }"><c:param name="searchKeyword" value="${searchVO.searchKeyword}"/></c:if>
	</c:url>
	
	<!-- content 시작 -->
	<div id="content">
		<div class="container">
			<div id="contents">
				<div id="bbs_wrap">
					<div class="board_view">
						<dl class="tit_view">
							 <dt> 프로그램명 </dt>  
							 <dd><c:out value="${result.resveSj }"></c:out></dd>
						</dl>
						
						<dl class="tit_view">
							<dt> 신청유형 </dt>  
						  	<dd>
						  		<c:choose>
						  			<c:when test="${result.resveSeCode eq 'TYPE01'}">선착순</c:when>
						  			<c:when test="${result.resveSeCode eq 'TYPE02'}">승인관리</c:when>
						  		</c:choose>
							</dd>
						</dl>
						<dl class="tit_view">
							 <dt> 강사명 </dt>  
							 <dd><c:out value="${result.recNm }"></c:out></dd>
						</dl>
						<dl class="tit_view">
							 <dt> 운영일자 </dt>  
							 <dd><c:out value="${result.useBeginDt }"/> ~ <c:out value="${result.useEndDt }"/></dd>
							 <dt> 운영시간 </dt>  
							 <dd><c:out value="${result.useBeginTime } ~ ${result.useEndTime }"/></dd>
							 <dt> 신청기간 </dt>  
							 <dd><c:out value="${result.reqstBgnde }"/> ~ <c:out value="${result.reqstEndde }"/></dd>
							 <dt> 신청 가능한 인원 </dt>  
							 <dd><c:out value="${result.maxAplyCnt }"/></dd>
						</dl>
						<dl class="info_view2">
							 <dt> 작성자ID </dt>  
							 <dd><c:out value="${result.frstRegisterId }"></c:out></dd>
							 <dt> 작성일 </dt>  
							 <dd><fmt:formatDate value="${result.frstRegistPnttm }" pattern="yyyy-MM-dd"/></dd>
						</dl>
						<div class="view_cont">
							<c:out value="${result.resveCn }" escapeXml="false"/>
						</div>
					</div>
					
					<div class="btn-cont ar">
						<c:choose>
							<c:when test="${result.applyStatus eq '1' }"><a href="#" class="btn btn-status" data-status="${result.applyStatus }">접수 대기중</a></c:when>
							<c:when test="${result.applyStatus eq '2' }"><a href="/rsv/rsvApplyRegist.do${_BASE_PARAM}" id="btn-apply" class="btn spot">신청</a></c:when>
							<c:when test="${result.applyStatus eq '3' }"><a href="#" class="btn btn-status" data-status="${result.applyStatus }">접수 마감</a></c:when>
							<c:when test="${result.applyStatus eq '4' }"><a href="#" class="btn btn-status" data-status="${result.applyStatus }">운영중</a></c:when>
							<c:otherwise><a href="#" class="btn btn-status" data-status="${result.applyStatus }">종료</a></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${param.listType eq 'calendar' }">
								<c:url var="listUrl" value="/rsv/selectCalendar.do">
									<c:param name="searchDate" value="${searchVO.searchDate }"></c:param>
								</c:url>
							</c:when>
							<c:otherwise>
								<c:url var="listUrl" value="/rsv/selectList.do${_BASE_PARAM }"/>
							</c:otherwise>
						</c:choose>
						<a href="${listUrl}" class="btn">목록</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- contents 끝 -->
	
	<script>
		$(document).ready(function () {
			// 예약상태 메세지
			$(".btn-status").click(function () {
				var status = $(this).data("status");
				
				if (status == "1") {
					alert("현재 접수대기중 상태 입니다.")
				}
				else if (status == "3") {
					alert("현재 접수마감 상태 입니다.")
				}
				else if (status == "4") {
					alert("현재 운영중 상태 입니다.")
				}
				else if (status == "5") {
					alert("현재 종료 상태 입니다.")
				}
			});
			
			// 신청
			$("#btn-reg").click(function () {
				if(!confirm("신청 하시겠습니까?")) {
					return false;
				}
			});
			
			// 신청가능여부 체크 후 진행
			$("#btn-apply").click(function () {
				var href = $(this).attr("href");
				
				$.ajax({
					type : "POST",
					url : "/rsv/rsvCheck.json",
					data: {"resveId" : "${searchVO.resveId}"},
					dataType : "json",
					success : function(result) {
						if(result.successYn == "Y") {
							location.href = href;
						}
						else {
							alert(result.message);
						}
					}, error : function(result) {
						alert("error");
					}
				});
				
			});
			
		});
	
	</script>

</body>
</html>