<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 연습</title>
</head>
<body>

<c:set var="step" value="${param.step}"/>
<c:if test="${empty step}">
	<c:set var="step" value="1"/>
</c:if>

<ul>
	<c:choose>
		<%-- c tag 설명 --%>
		<%-- c:out --%>
		<c:when test="${step eq '1'}">
			<li>
				<h3>c:out Tag : &#60;% = ... %&#62;과 유사한 표현식</h3>
				<h4>기본문법 : &#60;c:out value="값"/&#62;</h4>
				
				JSP 1번 : <% out.print("hello~"); %> <br/>	<%-- <% %> = JSP 문법 --%>
				JSP 2번 : <% String a = "hello~";%>			<%-- 스트링 a변수에 값 지정 --%>
						 <%=a%><br/>						
				JSTL : <c:out value="hello~"/><br/>
			</li>			
		</c:when>
		
		<%-- c:import --%>
		<c:when test="${step eq '2'}">
			<li>
				<h3>c:import Tag : jsp 'include'와 유사. 서버 내부 또는 서버 외부의 모든 자원 컨텐츠를 포함하는 추가 기능이 있음</h3>
				<h4>기본문법 : &#60;c:import url="URL주소 값 " charEncoding="utf-8"/&#62;</h4>
				
				<c:import url="/temp/jstlImport.do" charEncoding="utf-8"/>
				<%-- <c:import url="/temp/jstlImport.do" charEncoding="utf-8"/> --%>
			</li>			
		</c:when>
		
		<%-- c:set --%>
		<c:when test="${step eq '3'}">
			<li>
				<h3>c:set Tag : 일반 변수를 생성해서 값을 할당</h3>
				<h4>기본문법 : &#60;c:set var="변수명 " value="값"/&#62;</h4>
				
				<c:set var="str" value="지금은 c:set 연습중"/>
				<c:out value="${str}"/>				
			</li>			
		</c:when>
		
			<%-- c:if --%>
		<c:when test="${step eq '4'}">
			<li>
				<h3>c:if Tag : 조건문 중의 하나. JAVA에서 사용하는 if와 동일</h3>
				<h4>기본문법 : &#60;c:if test="조건문"&#62; &#60;/c:if&#62;</h4>
				
				<c:set var="test" value="0"/>
				<c:if test="${test eq '0'}">	<%-- 조건은 test로 해줘야 함 --%>
					true 0입니다.
				</c:if>	
				<c:if test="${test eq '1'}">	<%-- 조건은 test로 해줘야 함 --%>
					true 1입니다.
				</c:if>	
			</li>			
		</c:when>
		
			<%-- c:choose, c:when, c:otherwise --%>
		<c:when test="${step eq '5'}">
			<li>
				<h3>c:choose, c:when, c:otherwise Tag : 조건문 중의 하나. JAVA에서 사용하는 if, else if, else와 비슷 함</h3>
				<h4>기본문법 : <br/>
					&#60;c:choose&#62;<br/>
						&#60;c:when test="조건문"&#62; 조건 true에 대한 내용 &#60;/c:when&#62;<br/>
						&#60;c:otherwise&#62;조건에 해당되지 않아서 나오는 내용 &#62;/c:otherwise&#62;<br/>
					&#60;/c:choose&#62;
				</h4>
				
				<c:set var="test" value="0"/>
				<c:choose>
					<c:when test="${test eq '1'}">1입니다</c:when>
					<c:otherwise>조건에 해당되지 않음</c:otherwise>	
				</c:choose>
				<br/>
				<c:choose>
					<c:when test="${test eq '0'}">1입니다</c:when>
					<c:otherwise>조건에 해당되지 않음</c:otherwise>	
				</c:choose>	
			</li>			
		</c:when>
	</c:choose>
</ul>

</body>
</html>