<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>데이터 가져오기~</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
<style>
table{border-collapse: collapse;}
th{font-weight: bold;}
th, td{padding: 5px; border: 1px solid #000;}
</style>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>TEMP_ID</th>
                <th>TEMP_VAL</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="result" items="${resultList }">		<!-- jstl를 쓰겠다 , 반복문-->
                <tr>
                    <td><c:out value="${result.tempId}"/></td>	<!-- 씨 아웃 = 시스아웃하고 동일, 해킹방지(보안)-->
                    <td>
	                    <c:url var="viewUrl" value="/temp/select.do">
	                    	<c:param name="tempId" value="${result.tempId}"/>
	                    </c:url>
	                    <a href="${viewUrl}"><c:out value="${result.tempVal}"/></a>
                    </td>   
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button type="button" id="btn-reg" data-href="/temp/tempRegist.do">등록하기</button>
    <script>
        $(document).ready(function(){
            //등록하기
//       #:아이디    선택자	   클릭을 하면 얘로 바꿔라
            $("#btn-reg").click(function(){
                location.href = $(this).data("href");	// a태그처럼 페이지 이동, a태그랑 동일
            });
        });
    </script>
</body>
</html>
