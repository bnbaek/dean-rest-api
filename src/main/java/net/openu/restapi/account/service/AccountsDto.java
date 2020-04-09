package net.openu.restapi.account.service;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import net.openu.restapi.account.repository.Accounts;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
public class AccountsDto {

  @Value
  public static class Request {

    private String email;
    private String name;
    private String phoneNumber;

    public Request(String email, String name, String phoneNumber) {
      this.email = email;
      this.name = name;
      this.phoneNumber = phoneNumber;
    }

    public Accounts toEntity() {
      return Accounts.builder()
          .name(name)
          .username(email)
          .phoneNumber(phoneNumber)
          .build();
    }
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
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

  public enum JoinStatus {
    SUCCESS, STAND_BY
  }

}
