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
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
<link rel="stylesheet" href="/asset/member/css/login.css">
</head>
<body>
	<div class="tit_intro_step">
		<ul>
			<li class="current">약관동의</li>
			<li>회원유형</li>
			<li>정보입력</li>
			<li>가입완료</li>
		</ul>
	</div>
	
	<h2 class="icon1">약관동의</h2>
	
	<p>약관 및 개인정보 수집 ㆍ 이용 및 제3자 제공 동의에 대한 내용을 자세히 읽어 보신 후 동의 여부를 결정하여 주시기 바랍니다.</p>
	
	<div class="agree_box">
		<form action="/join/memberType.do" id="agreeFrm" name="agreeFrm" method="post">
			<h3 class="icon2">이용약관(필수)</h3>
			<div class="cont" tabindex="0">
				이용약관 내용~
			</div>
			<div class="check">
				필수(
					<input type="radio" class="vMid" id="agree01" name="agree01">
					<label for="agree01">동의</label>
					<input type="radio" class="vMid" id="agree01-1" name="agree01" checked="checked">
					<label for="agree01-1">비동의</label>
					)
			</div>
			
			<h3 class="icon2">개인정보 수집 ㆍ 이용(필수)</h3>
			<div class="cont" tabindex="0">
				개인정보 수집 ㆍ 이용 내용~
			</div>
			<div class="check">
				필수(
					<input type="radio" class="vMid" id="agree02" name="agree02">
					<label for="agree02">동의</label>
					<input type="radio" class="vMid" id="agree02-1" name="agree02" checked="checked">
					<label for="agree02-1">비동의</label>
					)
			</div>
			
			<h3 class="icon2">개인정보 제3자 제공 및 위탁에 관한 안내</h3>
			<div class="cont" tabindex="0">
				개인정보 제3자 제공 및 위탁에 관한 안내 내용~
			</div>
			<div class="check">
				선택(
					<input type="radio" class="vMid" id="agree03" name="agree03">
					<label for="agree03">동의</label>
					<input type="radio" class="vMid" id="agree03-1" name="agree03" checked="checked">
					<label for="agree03-1">비동의</label>
					)
			</div>
		</form>
	</div>
	
	<div class="agree_all">
		<label><input type="checkbox" id="check_all"/>모든 약관에 동의합니다.</label>
	</div>
	<div class="btn-cont">
		<a href="/join/memberType.do" class="btn spot btn_next fn">다음</a>
	</div>
	
	<script>
		function cnfirm() {
			if(!$('input[id=agree01]:checked').is(':checked')){
				alert('이용약관에 동의하지 않으셨습니다.');
				return false;
			}
			if(!$('input[id=agree02]:checked').is(':checked')){
				alert('개인정보 수집 ㆍ 이용에 동의하지 않으셨습니다.');
				return false;
			}
			$("#agreeFrm").submit();
		}
		
		$(document).ready(function() {
			$("#check_all").click(function() {
				if($(this).is(":checked")){
					$("#agree01, #agree02, #agree03").click();
				}
			});
			
			$(".btn_next").click(function() {
				cnfirm();
				return false;
			});
		});
	</script>
	
</body>
</html>
