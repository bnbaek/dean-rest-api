package net.openu.restapi.account.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */

@Getter
@ToString

@Entity
public class Accounts implements UserDetails {

  public Accounts() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private Long id;

  private String uuid;

  @Column(nullable = false, unique = true, length = 30)
  private String username;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(nullable = false, length = 100)
  private String password;

  @Column(nullable = false, length = 30)
  private String name;

  private String phoneNumber;

  @Column(length = 100)
  private String provider;
  @Column(length = 100)
  private Long providerId;

  @Enumerated(EnumType.STRING)
  private AccountsDto.JoinStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  @ElementCollection(fetch = FetchType.EAGER)
  @Builder.Default
  private List<String> roles = new ArrayList<>();


  @Builder
  public Accounts(String username, String password, String name, String phoneNumber, List<String> roles) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.uuid = UuidUtils.generator();
    this.roles = roles;
    this.status = JoinStatus.STAND_BY;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
  }

  public Accounts updateStatus(AccountsDto.JoinStatus status) {
    this.status = status;
    this.modifiedAt = LocalDateTime.now();
    return this;
  }

  public Accounts updateProvider(String provider, Long providerId) {
    this.provider = provider;
    this.providerId = providerId;
    return this;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Override
  public boolean isEnabled() {
    return true;
  }
}
