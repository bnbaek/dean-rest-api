package net.openu.restapi.account.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import net.openu.restapi.account.repository.Accounts;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
public class AccountsDto {

  @Setter
  @Getter
  public static class Create {

    private String email;
    private String name;
    private String phoneNumber;

    public Create(String email, String name, String phoneNumber) {
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
