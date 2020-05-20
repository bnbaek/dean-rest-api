package net.openu.restapi.account.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by iopenu@gmail.com on 2020/04/13
 * Github : https://github.com/bnbaek
 */

@RequiredArgsConstructor
@Service
public class KakaoService {

  private final RestTemplate restTemplate;
  private final Environment env;
  private final Gson gson;
  @Value("${spring.url.base}")
  private String baseUrl;
  @Value("${spring.social.kakao.client_id}")
  private String kakaoClientId;
  @Value("${spring.social.kakao.redirect}")
  private String kakaoRedirect;


  public KakaoDto.KakaoAuth getKakaoTokenInfo(String code) {
    // Set header : Content-type: application/x-www-form-urlencoded
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    // Set parameter
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", kakaoClientId);
    params.add("redirect_uri", baseUrl + kakaoRedirect);
    params.add("code", code);
    // Set http entity
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("spring.social.kakao.url.token"), request, String.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      return gson.fromJson(response.getBody(), KakaoDto.KakaoAuth.class);
    }
    return null;
  }


  public KakaoDto.KakaoProfile getKakaoProfile(String accessToken) {
    // Set header : Content-type: application/x-www-form-urlencoded
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.set("Authorization", "Bearer " + accessToken);

    // Set http entity
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
    try {
      // Request profile
      ResponseEntity<String> response = restTemplate
          .postForEntity(env.getProperty("spring.social.kakao.url.profile"), request, String.class);
      if (response.getStatusCode() == HttpStatus.OK) {
        return gson.fromJson(response.getBody(), KakaoDto.KakaoProfile.class);
      }
    } catch (Exception e) {
      throw new RuntimeException("가입정보가 없습니다.");
    }
    throw new RuntimeException("가입정보가 없습니다.");
  }

}
