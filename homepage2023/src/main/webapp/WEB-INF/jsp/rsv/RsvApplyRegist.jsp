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
<title>예약자 정보 등록/수정</title>
	
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
		<c:when test="${not empty searchVO.reqstId}">
			<c:set var="actionUrl" value="/rsv/rsvApplyUpdate.do"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="actionUrl" value="/rsv/rsvApplyInsert.do"></c:set>
		</c:otherwise>
	</c:choose>
	
	<%-- 기본 URL --%>
	<c:url var="_BASE_PARAM" value="">
		<c:param name="resveId" value="${searchVO.resveId}"></c:param>
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
							 <dd><c:out value="${reservation.resveSj }"></c:out></dd>
						</dl>
						
						<dl class="tit_view">
							<dt> 신청유형 </dt>  
						  	<dd>
						  		<c:choose>
						  			<c:when test="${reservation.resveSeCode eq 'TYPE01'}">선착순</c:when>
						  			<c:when test="${reservation.resveSeCode eq 'TYPE02'}">승인관리</c:when>
						  		</c:choose>
							</dd>
						</dl>
						
						<dl class="tit_view">
							 <dt> 강사명 </dt>  
							 <dd><c:out value="${reservation.recNm }"></c:out></dd>
						</dl>
						
						<dl class="info_view">
							 <dt> 운영일자 </dt>  
							 <dd><c:out value="${reservation.useBeginDt }"/> ~ <c:out value="${reservation.useEndDt }"/></dd>
							 <dt> 운영시간 </dt>  
							 <dd><c:out value="${reservation.useBeginTime } ~ ${reservation.useEndTime }"/></dd>
							 <dt> 신청기간 </dt>  
							 <dd><c:out value="${reservation.reqstBgnde }"/> ~ <c:out value="${reservation.reqstEndde }"/></dd>
							 <dt> 신청 가능한 인원 </dt>  
							 <dd><c:out value="${reservation.maxAplyCnt }"/></dd>
						</dl>
						
						<dl class="info_view2">
							 <dt> 작성자ID </dt>  
							 <dd><c:out value="${reservation.frstRegisterId }"></c:out></dd>
							 <dt> 작성일 </dt>  
							 <dd><fmt:formatDate value="${reservation.frstRegistPnttm }" pattern="yyyy-MM-dd"/></dd>
						</dl>
						
						<div class="view_cont">
							<c:out value="${reservation.resveCn }" escapeXml="false"/>
						</div>
					</div>
				</div>
				
				<h3 class="icon1"> 신청정보 </h3>
				<form action="${actionUrl }" method="post" id="frm" name="frm" onsubmit="return regist()">
					<input type="hidden" name="resveId" value="${searchVO.resveId }"/>
					<input type="hidden" name="resveSeCode" value="${reservation.resveSeCode }"/>
					<input type="hidden" name="reqstId" value="${searchVO.reqstId }"/>
					<input type="hidden" name="resveDe" value="${reservation.resveSeCode }"/>
					
					<table class="chart2">
						<caption> 예약자정보 작성 </caption>
						<colgroup>
							<col style="width:120px"/>
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">예약자명</th>
								<td>
									<input type="text" id="chargerNm" name="chargerNm" title="예약자명" value='<c:out value="${result.chargerNm }" default="${USER_INFO.name }"/>'/>
								</td>
							</tr>
							<tr>
								<th scope="row">연락처</th>
								<td>
									<input type="text" id="telno" name="telno" title="연락처" value='<c:out value="${result.telno }"/>'/>
								</td>
							</tr>
							<tr>
								<th scope="row">이메일</th>
								<td>
									<input type="text" id="email" name="email" title="이메일" value='<c:out value="${result.email }" default="${USER_INFO.email }"/>'/>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="btn-cont ar">
						<c:choose>
							<c:when test="${not empty searchVO.reqstId }">
								<c:url var="uptUrl" value="/rsv/rsvApplyUpdate.do${_BASE_PARAM}">
									<c:param name="reqstId" value="${result.reqstId }"/>
								</c:url>
								<a href="${uptUrl }" id="btn-reg" class="btn">수정</a>
								
								<c:url var="delUrl" value="/rsv/rsvApplyDelete.do${_BASE_PARAM}">
									<c:param name="reqstId" value="${result.reqstId }"/>
								</c:url>
								<a href="${delUrl }" id="btn-del" class="btn"><i class="ico-del"></i>신청취소</a>
							</c:when>
							<c:otherwise>
								<a href="#none" id="btn-reg" class="btn spot">신청</a>
							</c:otherwise>
						</c:choose>
						<c:url var="listUrl" value="/rsv/seleteList.do${_BASE_PARAM }"/>
						<a href="${listUrl }" class="btn">취소</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- contents 끝 -->
	
	<script>
		$(document).ready(function () {
			//예약정보 등록
			$("#btn-reg").click(function() {
				$("#frm").submit();
				return false;
			});
			
			//예약 글 삭제
			$("#btn-del").click(function () {
				if(!confirm("삭제하시겠습니까?")) {
					return false;
				}
			});
		});
		
		function regist() {
			if(!$("#chargerNm").val()) {
				alert("예약자명을 입력해주세요.");
				$("#chargerNm").focus();
				return false
			}
			else if(!$("#telno").val()) {
				alert("연락처를 입력해주세요.");
				$("#telno").focus();
				return false
			}
		}
	</script>

</body>
</html>