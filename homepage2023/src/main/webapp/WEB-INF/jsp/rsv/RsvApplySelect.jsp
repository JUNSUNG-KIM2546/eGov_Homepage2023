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
<title>예약자 상세정보</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
	
	<%-- BBS Style --%>
	<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
	<%-- 공통 Style --%>
	<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />
	
	<%-- 기본 URL --%>
	<c:url var="_BASE_PARAM" value="">
		<c:param name="pageIndex" value="${searchVO.pageIndex}"></c:param>
		<c:if test="${not empty searchVO.searchCondition }"><c:param name="searchCondition" value="${searchVO.searchCondition}"/></c:if>
		<c:if test="${not empty searchVO.searchKeyword }"><c:param name="searchKeyword" value="${searchVO.searchKeyword}"/></c:if>
	</c:url>
	
	<!-- content 시작 -->
	<div id="content">
		<div class="container">
			<div id="contents">
				<form action="/rsv/rsvApplyUpdate.do" method="post" id="frm" name="frm" onsubmit="return regist">
					<input type="hidden" name="reqstId" value="${result.reqstId }">
					
					<table class="chart2">
						<caption> 예약정보 작성 </caption>
						<colgroup>
							<col style="width:120px"/>
							<col />
						</colgroup>
						<tbody>
							<!-- 접수중일 떄만 수정가능 -->
							<c:choose>
								<c:when test="${result.confmSeCode eq 'R' }">
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
											<input type="text" id="email" name="email" title="이메일" value='<c:out value="${result.email }"/>'/>
										</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<th scope="row">신청자명</th>
										<td> <c:out value="${result.chargerNm }"/>(<c:out value="${result.frstRegisterId }"/>) </td>
									</tr>
									<tr>
										<th scope="row">연락처</th>
										<td>
											<c:out value="${result.telno }"/>
										</td>
									</tr>
									<tr>
										<th scope="row">이메일</th>
										<td>
											<c:choose>
												<c:when test="${empty result.email }">없음</c:when>
												<c:otherwise>
													<c:out value="${result.email }"/>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<th scope="row">승인여부</th>
										<td>
											<c:choose>
												<c:when test="${result.confmSeCode eq 'R' }">신청 접수 중</c:when>
												<c:when test="${result.confmSeCode eq 'O' }">신청 승인</c:when>
												<c:when test="${result.confmSeCode eq 'X' }">신청반려</c:when>
											</c:choose>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
							
							<!-- 반려일 시 반려가유에 대해서 나옴 -->
							<c:if test="${result.confmSeCode eq 'X' }">
								<tr>
									<th>반려사유</th>
									<td><c:out value="${result.returnResn }"></c:out></td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<div class="btn-cont ar">
						<!-- 접수중만 수정가능 -->
						<c:if test="${result.confmSeCode eq 'R' }">
							<a href="/rsv/rsvApplyUpdate.do" id="btn-upt" class="btn">수정</a>
							<c:url var="delUrl" value="/rsv/rsvApplyDelete.do${_BASE_PARAM }">
								<c:param name="reqstId" value="${result.reqstId }"/>
							</c:url>
							<a href="${delUrl }" id="btn-del" class="btn"><i class="ico-del"></i>삭제</a>
						</c:if>
						<c:url var="listUrl" value="/rsv/selectApplyList.do${_BASE_PARAM }"/>
						<a href="${listUrl }" class="btn">목록</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- contents 끝 -->
	
	<script>
		$(document).ready(function() {
			// 예약자 삭제
			$("#btn-del").click(function() {
				if(!confirm("삭제하시겠습니까?")) {
					return false;
				}
			});
			
			$("#btn-upt").click(function() {
				$("#frm").submit();
				return false;
			});
		});
		
		// vali
		function regist() {
			if(!$("#chargerNm").val()){
				alert("예약자명을 입력해주세요.");
				$("#chargerNm").focus();
				return false;
			}
			else if(!$("#telno").val()){
				alert("연락처를 입력해주세요.");
				$("#telno").focus();
				return false;
			}
		}
	</script>

</body>
</html>