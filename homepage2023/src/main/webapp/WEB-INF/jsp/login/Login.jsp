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
<title>로그인 페이지</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>	<!-- 여기 버전을 가져오겠다 (교육용), 회사에서는 회사에 맞는 버전으로 가져와야함 -->
<link rel="stylesheet" href="/asset/member/css/login.css">
</head>
<body>
	<h2 class="icon1"> 로그인 </h2>
	<p class="mB20"> 스마트소프트웨어 하이테크과정 홈페이지 방문을 환영합니다. </p>
	
	<div class="login_box">
		<div class="mem_login">
			<div class="smartshool login">
				<div class="login_inp_wrap">
					<div class="login_inp">
						<form action="/login/actionLogin.do" name="frmGnrlLogin" method="post" id="frmGnrlLogin" onsubmit="return checkGnrlLogin(this)">
							<fieldset>
								<legend> 로그인 입력폼 </legend>
								<span>
									<label for="id">	<img alt="아이디" 	src="/asset/member/images/member/ico_id.svg"></label>
									<input type="text" 		id="id" 	name="id" 		value="" class="inp" title="아이디를 입력하세요" 	placeholder="아이디를 입력하세요.">
								</span>
								<span>
									<label for="pwd">	<img alt="패스워드" 	src="/asset/member/images/member/ico_pw.svg"></label>
									<input type="password" 	id="pwd" 	name="password" value="" class="inp" title="비밀번호를 입력하세요" 	placeholder="비밀번호를 입력하세요." 	autocomplete="off">
								</span>
								<button type="submit" class="btn-lg spot p10"> 로그인 </button>
							</fieldset>
						</form>
					</div>
					<ul>
						<li><p> 회원으로 가입하시겠습니까?	</p><a href="/join/siteUseAgree.do">	회원가입		</a></li>
						<li><p> 아이디를 잊으셨나요? 	</p><a href="/member/findId.do">		아이디 찾기	</a></li>
						<li><p> 비밀번호를 잊으셨나요?	</p><a href="/member/findPassword.do">	패스워드 찾기	</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	
		<c:if test="${not empty loginMessage}">
			alert("${loginMessage}");
		</c:if>
		
		function checkGnrlLogin(frm) {
			if(frm.id.value == "") {
				alert("아이디를 입력해주세요.");
				frm.id.focus();
				return false;
			}
			
			if(frm.pwd.value == "") {
				alert("비밀번호를 입력해주세요.");
				frm.pwd.focus();
				return false;
			}
			
			$("#frmGnrlLogin");
		}
		
		$(document).ready(function() {
			//아이디 입력 창 포커스 설정
			$('#id').focus();
		})
		
	</script>
	
</body>
</html>