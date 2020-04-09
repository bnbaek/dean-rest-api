package net.openu.restapi.account.repository;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.openu.restapi.account.service.AccountsDto;
import net.openu.restapi.account.service.AccountsDto.JoinStatus;
import net.openu.restapi.utils.UuidUtils;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */

@Getter
@ToString

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Accounts {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private Long id;

  private String uuid;

  @Column(nullable = false, unique = true, length = 30)
  private String username;

  @Column(nullable = false, length = 30)
  private String name;

  private String phoneNumber;

  private AccountsDto.JoinStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;


  @Builder
  public Accounts(String username, String name, String phoneNumber) {
    this.username = username;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.uuid = UuidUtils.generator();
    this.status = JoinStatus.STAND_BY;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
  }
}
