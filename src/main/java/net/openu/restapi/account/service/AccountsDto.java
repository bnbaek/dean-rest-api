package net.openu.restapi.account.service;

import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.openu.restapi.account.repository.Accounts;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
public class AccountsDto {

  @Getter
  @Setter
  public static class Create {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;

    public Create(String email, String name, String phoneNumber, String password) {
      this.email = email;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.password = password;
    }

    public Accounts toEntity(String password) {
      return Accounts.builder()
          .name(name)
          .username(email)
          .phoneNumber(phoneNumber)
          .password(password)
          .roles(Collections.singletonList("ROLE_USER"))
          .build();
    }
  }

  @Getter
  @Setter
  public static class Login {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

  }

  @Getter
  @Setter
  public static class KaKaoLogin {

    @NotBlank
    private String accessToken;

    public KaKaoLogin(@NotBlank String accessToken) {
      this.accessToken = accessToken;
    }
  }



  @ToString
  @Getter
  public static class LoginResponse {

    private String email;
    private String accessToken;


    public LoginResponse(String email, String accessToken) {
      this.email = email;
      this.accessToken = accessToken;
    }
  }


  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @ToString
  public static class Response {

    private String uuid;
    private String email;
    private String name;
    private JoinStatus status;

    @Builder
    public Response(String uuid, String email, String name, JoinStatus status) {
      this.uuid = uuid;
      this.email = email;
      this.name = name;
      this.status = status;
    }

    public static Response of(Accounts account) {
      return Response.builder()
          .uuid(account.getUuid())
          .email(account.getUsername())
          .name(account.getName())
          .status(account.getStatus())
          .build();

    }
  }

  @Getter
  @Setter
  public static class UpdateStatus {

    @NotNull
    private AccountsDto.JoinStatus status;
    @NotEmpty
    private String orderer;

    public Accounts apply(Accounts account) {
      return account.updateStatus(status);
    }

  }

  @Getter
  public static class InterLock {

    private String provider;
    private Long providerId;

    public Accounts apply(Accounts account){
      return account.updateProvider(provider,providerId);
    }

    public InterLock(String provider, Long providerId) {
      this.provider = provider;
      this.providerId = providerId;
    }

  }

  @Getter
  public static class ResponseOne {

    private Response account;

    public ResponseOne(Response account) {
      this.account = account;
    }
  }

  @Getter
  public static class ResponseList {

    private List<Response> accounts;

    public ResponseList(List<Response> accounts) {
      this.accounts = accounts;
    }
  }

  public enum JoinStatus {
    SUCCESS, STAND_BY
  }


}
