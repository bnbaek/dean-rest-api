package net.openu.restapi.api.account.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by BNBAEK
 * Package : net.openu.restapi.api.account.v1
 * User: dean
 * Date: 2020/04/13
 * Time: 6:02 오후
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/social/login/kakao")
public class AccountPageController {
  private final Environment env;

  @Value("${spring.url.base}")
  private String baseUrl;

  @Value("${spring.social.kakao.client_id}")
  private String kakaoClientId;

  @Value("${spring.social.kakao.redirect}")
  private String kakaoRedirect;


  /**
   * 카카오 로그인 페이지
   */
  @GetMapping
  public ModelAndView socialLogin(ModelAndView mav) {

    StringBuilder loginUrl = new StringBuilder()
        .append(env.getProperty("spring.social.kakao.url.login"))
        .append("?client_id=").append(kakaoClientId)
        .append("&response_type=code")
        .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect);

    mav.addObject("loginUrl", loginUrl);
    mav.setViewName("social/login");
    return mav;
  }


}