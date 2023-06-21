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
			<li>약관동의</li>
			<li class="current">회원유형</li>
			<li>정보입력</li>
			<li>가입완료</li>
		</ul>
	</div>
	
	<h2 class="icon1">회원유형 선택</h2>
	<div class="confirm-area user-area">
		<article>
			<h3 class="icon2 ico-user">일반회원</h3>
			<div class="confirm_box">
				<p class="mB20">일반회원</p>
				<div class="btn-cont"><a href="/join/memberRegist.do?loginType=normal" class="btn spot member-type"><span>회원가입</span></a></div>
			</div>
		</article>
		
		<article>
			<h3 class="icon2 ico-user">SNS회원</h3>
			<div class="confirm_box">
				<p class="mB20">카카오 회원</p>
				<div class="btn-cont">
					<a class="btn-kakao" href="#" data-type="join">
						<img alt="카카오 로그인 버튼" src="http://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="150" height="50"/>
					</a>
				</div>
			</div>
		</article>
		
		<article>
			<h3 class="icon2 ico-user">SNS회원</h3>
			<div class="confirm_box">
				<p class="mB20">네이버 회원</p>
				<div class="btn-cont">
					<a class="btn-naver" href="${naverAuthUrl}" data-type="join">
						<img alt="네이버 로그인 버튼" src="/asset/front/images/common/btn-naver.png" width="150" height="50"/>
					</a>
				</div>
			</div>
		</article>
		
	</div>
	
</body>
</html>

