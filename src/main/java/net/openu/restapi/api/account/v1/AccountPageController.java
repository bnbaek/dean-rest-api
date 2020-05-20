package net.openu.restapi.api.account.v1;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openu.restapi.account.service.AccountService;
import net.openu.restapi.account.service.AccountsDto.KaKaoLogin;
import net.openu.restapi.account.service.AccountsDto.LoginResponse;
import net.openu.restapi.account.service.AccountsDto.Response;
import net.openu.restapi.account.service.KakaoDto.KakaoAuth;
import net.openu.restapi.account.service.KakaoService;
import net.openu.restapi.api.account.v1.AccountSession.Type;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.api.account.v1
 * User: dean
 * Date: 2020/04/13
 * Time: 6:02 오후
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/social/login/kakao")
public class AccountPageController {

  private final Environment env;
  private final KakaoService kakaoService;
  private final AccountService accountService;


  @Resource
  private AccountSession accountSession;

  @Value("${spring.url.base}")
  private String baseUrl;

  @Value("${spring.social.kakao.client_id}")
  private String kakaoClientId;

  @Value("${spring.social.kakao.redirect}")
  private String kakaoRedirect;


  /**
   * 카카오 연동 페이지
   */
  @GetMapping(value = "/interlock")
  public ModelAndView socialInterLock(ModelAndView mav, @RequestParam String uuid, HttpSession session) {

    String sessionId = session.getId();
    log.info("[interlock]sessionId = " + sessionId);

    StringBuilder loginUrl = new StringBuilder()
        .append(env.getProperty("spring.social.kakao.url.login"))
        .append("?client_id=").append(kakaoClientId)
        .append("&response_type=code")
        .append("&state=interlock_" + uuid)
        .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect);

    //session 정보
    accountSession.setUuid(uuid);
    accountSession.setType(Type.INTERLOCK);

    mav.addObject("loginUrl", loginUrl);
    mav.setViewName("social/interlock");
    return mav;
  }

  @GetMapping
  public ModelAndView socialLogin(ModelAndView mav, HttpSession session) {

    String sessionId = session.getId();
    log.info("[interlock]sessionId = " + sessionId);

    StringBuilder loginUrl = new StringBuilder()
        .append(env.getProperty("spring.social.kakao.url.login"))
        .append("?client_id=").append(kakaoClientId)
        .append("&response_type=code")
        .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect);

    //session 정보
    accountSession.setType(Type.LOGIN);

    mav.addObject("loginUrl", loginUrl);
    mav.setViewName("social/login");
    return mav;
  }




  /**
   * 카카오 인증 완료 후 리다이렉트 화면
   */
  @GetMapping(value = "/code")
  public ModelAndView redirectKakao(ModelAndView mav, @RequestParam String code, HttpSession session) {

    String sessionId = session.getId();
    log.info("[CODE]sessionId = " + sessionId);


    KakaoAuth kakaoTokenInfo = kakaoService.getKakaoTokenInfo(code);

    if (kakaoTokenInfo == null) {
      log.error("연동에 실패하였습니다");
      throw new RuntimeException("연동에 실패하였습니다.");
    }

    if (accountSession.getType() == Type.INTERLOCK) {
      log.info(accountSession.getUuid());
      Response response = accountService.kakaoInterLock(accountSession.getUuid(), kakaoTokenInfo.getAccess_token());
      mav.addObject("interLockInfo", response.toString());
    }

    if (accountSession.getType() == Type.LOGIN){

      log.info(accountSession.getUuid());

      LoginResponse loginResponse = accountService.kakaoLogin(new KaKaoLogin(kakaoTokenInfo.getAccess_token()));
      mav.addObject("authInfo",loginResponse.toString());
    }

    mav.setViewName("social/redirectKakao");
    return mav;
  }


}
