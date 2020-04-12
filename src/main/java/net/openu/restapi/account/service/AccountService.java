package net.openu.restapi.account.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openu.restapi.account.repository.Accounts;
import net.openu.restapi.account.repository.AccountsRepository;
import net.openu.restapi.account.service.AccountsDto.Create;
import net.openu.restapi.account.service.AccountsDto.JoinStatus;
import net.openu.restapi.account.service.AccountsDto.LoginResponse;
import net.openu.restapi.account.service.AccountsDto.UpdateStatus;
import net.openu.restapi.api.exception.AlreadyExistsException;
import net.openu.restapi.api.exception.EmailSigninFailedException;
import net.openu.restapi.api.exception.NotFoundException;
import net.openu.restapi.api.exception.UnauthorizedException;
import net.openu.restapi.config.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
@Slf4j

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

  private final AccountsRepository accountsRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Transactional
  public AccountsDto.Response signUp(Create create) {
    Boolean existAccount = accountsRepository.existsByUsername(create.getEmail());
    //중복체크
    if (existAccount) {
      throw new AlreadyExistsException("accounts", "email", create.getEmail());
    }
    Accounts savedAccount = accountsRepository.save(create.toEntity(passwordEncoder.encode(create.getPassword())));

    return AccountsDto.Response.of(savedAccount);

  }

  public List<AccountsDto.Response> findAll() {
    return accountsRepository.findAll().stream()
        .map(AccountsDto.Response::of)
        .collect(Collectors.toList());


  }

  public AccountsDto.Response findByUsername(String email) {

    return accountsRepository.findByUsername(email)
        .map(AccountsDto.Response::of)
        .orElseThrow(() -> new NotFoundException(email));
  }

  public AccountsDto.Response updateStatus(String uuid, UpdateStatus updateStatus) {
    log.info("{}에 의해 {}의 상태{}로 업데이트를 시도했다", updateStatus.getOrderer(), uuid, updateStatus.getStatus());

    Accounts updatedStatus = accountsRepository.findByUuid(uuid)
        .map(updateStatus::apply)
        .orElseThrow(NotFoundException::new);

    return AccountsDto.Response.of(updatedStatus);
  }

  public LoginResponse signIn(AccountsDto.Login login) {
    Accounts accounts = accountsRepository.findByUsername(login.getEmail()).orElseThrow(EmailSigninFailedException::new);

    if (accounts.getStatus() == JoinStatus.STAND_BY) {
      //관리자 승인 대기중 UNAUTHORIZED
      throw new UnauthorizedException("해당 계정은 관리자 승인 대기중 입니다.");
    }

    if (!passwordEncoder.matches(login.getPassword(), accounts.getPassword())) {
      throw new EmailSigninFailedException();
    }

    return new LoginResponse(login.getEmail(), jwtTokenProvider.createToken(accounts.getUuid(), accounts.getRoles()));
  }
}
