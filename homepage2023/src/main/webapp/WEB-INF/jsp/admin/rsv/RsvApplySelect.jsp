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
<title>(관리자)예약자 상세정보</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
	
	<%-- BBS Style --%>
	<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
	<%-- 공통 Style --%>
	<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />
	
	<%-- 기본 URL --%>
	<c:url var="_BASE_PARAM" value="">
		<c:param name="resveId" value="${searchVO.resveId}"></c:param>
		<c:param name="pageIndex" value="${searchVO.pageIndex }"></c:param>
		<c:if test="${not empty searchVO.searchCondition }"><c:param name="searchCondition" value="${searchVO.searchCondition}"/></c:if>
		<c:if test="${not empty searchVO.searchKeyword }"><c:param name="searchKeyword" value="${searchVO.searchKeyword}"/></c:if>
	</c:url>
	
	<!-- content 시작 -->
	<div id="content">
		<div class="container">
			<div id="contents">
				<form action="/admin/rsv/rsvApplyConfirm.do" method="post" id="frm" name="frm" onsubmit="return regist">
					<input type="hidden" name="resveId" value="${result.resveId }">
					<input type="hidden" name="reqstId" value="${result.reqstId }">
					
					<table class="chart2">
						<caption> 예약정보 작성 </caption>
						<colgroup>
							<col style="width:120px"/>
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">신청자명</th>
								<td> <c:out value="${result.chargerNm }"/>(<c:out value="${result.frstRegisterId }"/>) </td>
							</tr>
							<tr>
								<th scope="row">연락처</th>
								<td> <c:out value="${result.telno }"/> </td>
							</tr>
							<tr>
								<th scope="row">이메일</th>
								<td>
									<c:choose>
										<c:when test="${empty result.email }">없음</c:when>
										<c:otherwise>
											<c:out value="${result.email }"></c:out>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th scope="row">승인여부</th>
								<td>
									<select id="confmSeCode" name="confmSeCode">
										<option value="R"> 접수 </option>
										<option value="O" <c:if test="${result.confmSeCode eq 'O' }">selected="selected"</c:if>> 승인완료 </option>
										<option value="X" <c:if test="${result.confmSeCode eq 'X' }">selected="selected"</c:if>> 반려 </option>
									</select>
								</td>
							</tr>
							<tr id="resn" <c:if test="${result.confmSeCode ne 'X' }">style="display:none;"</c:if>>
								<th>반려사유</th>
								<td>
									<textarea rows="5" id="returnResn" name="returnResn"><c:out value="${result.returnResn }"/></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="btn-cont ar">
						<c:choose>
							<c:when test="${not empty searchVO.resveId }">
								<a href="#" id="btn-upt" class="btn">수정</a>
								<c:url var="delUrl" value="/admin/rsv/rsvApplyDelete.do${_BASE_PARAM }">
									<c:param name="reqstId" value="${result.reqstId }"/>
								</c:url>
								<a href="${delUrl }" id="btn-del" class="btn"><i class="ico-del"></i>삭제</a>
							</c:when>
							<c:otherwise>
								<a href="#none" id="btn-reg" class="btn spot">등록</a>
							</c:otherwise>
						</c:choose>
						<c:url var="listUrl" value="/admin/rsv/selectApplyList.do${_BASE_PARAM }"/>
						<a href="${listUrl }" class="btn">목록</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- contents 끝 -->
	
	<script>
		$(document).ready(function() {
			
			// 반려여부
			$("#confmSeCode").click(function() {
				var val = $(this).val();
				
				if(val == "X") {
					$("#resn").show();
				}
				else {
					$("#resn").hide();
				}
			});
			
			// 예약자 삭제
			$("#btn-del").click(function() {
				if (!confirm("삭제하시겠습니까?")) {
					return false;
				}
			});
			
			//승인
			$("#btn-upt").click(function() {
				$("#frm").submit();
				return false;
			})
		});
		
		// vali
		function regist() {
			if($("#confmSeCode").val() == "X" && !$("#returnResn").val()){
				alert("반려 사유를 작성해주세요.");
				$("#returnResn").focus();
				return false;
			}
		}
	</script>

</body>
</html>