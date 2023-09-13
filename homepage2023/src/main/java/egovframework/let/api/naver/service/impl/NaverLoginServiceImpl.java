package egovframework.let.api.naver.service.impl;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import egovframework.com.cmm.service.Globals;
import egovframework.let.api.naver.service.NaverLoginApi;
import egovframework.let.api.naver.service.NaverLoginService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service("naverLoginService")
public class NaverLoginServiceImpl extends EgovAbstractServiceImpl implements NaverLoginService {
	// 인증 요청문을 구성하는 파라미터
	private final static String CLIENT_ID = Globals.NAVER_CLIENTID;
	private final static String CLIENT_SECRET = Globals.NAVER_CLIENTSECRET;
	private final static String REDIRECT_URI = Globals.NAVER_REDIRECTURI;
	private final static String SESSION_STATE = "oauth_state";
	// 프로필 조회 API URL
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";

	// 네이버 아이디로 인증 URL 생성 Method
	public String getAuthorizationUrl(HttpSession session, String domain, String port) {
		String redirectUri = "http://" + domain + ":" + port + REDIRECT_URI;
		
		// 세션 유효성 검증을 위하여 난수를 생성
		String state = generateRandomString();
		
		//생성한 난수 값을 session에 저장
		setSession(session, state);
		
		// Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(redirectUri)
				.state(state)	// 앞서 생성한 난수값을 인증 URL생성시 사용함
				.build(NaverLoginApi.instance());
		return oauthService.getAuthorizationUrl();
	}

	// 네이버아이디로 CallBack 처리 및 AccessToken 획득 Method
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state, String domain, String port) throws IOException {
		String redirectUri = "http://" + domain + ":" + port + REDIRECT_URI;
		
		// Callback으로 전달받은 세션검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인
		String sessionState = getSession(session);
		if(StringUtils.pathEquals(sessionState, state)) {
			
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(redirectUri)
					.build(NaverLoginApi.instance());
			// Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

	// 세션 유효성 검증을 위한 난수 생성기
	public String generateRandomString() {
		return UUID.randomUUID().toString();
	}

	// http session에 데이터 저장
	public void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}

	// http session에서 데이터 가져오기
	public String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}

	// Access Token을 이용하여 네이버 사용자 프로필 API 호출
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		Request request = new Request.Builder()
				.url(PROFILE_API_URL)
				.method("GET", null)
				.addHeader("Content-type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "Bearer " + oauthToken.getAccessToken())
				.build();
		Response response = client.newCall(request).execute();

		return response.body().string();
	}
	
}
