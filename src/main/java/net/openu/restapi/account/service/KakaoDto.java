package net.openu.restapi.account.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.openu.restapi.account.service.AccountsDto.InterLock;

/**
 * Created by iopenu@gmail.com on 2020/04/13
 * Github : https://github.com/bnbaek
 */
public class KakaoDto {

  @Getter
  @Setter
  @AllArgsConstructor
  @ToString
  public static class KakaoAuth {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private long expires_in;
  }


  @Getter
  @Setter
  public class KakaoProfile {

    private Long id;
    private KakaoProperties properties;

    public InterLock toEntity() {
      return new InterLock("KAKAO", id);
    }
  }

  @Getter
  @Setter
  private class KakaoProperties {

    private String nickname;
    private String thumbnail_image;
    private String profile_image;
  }
}
