package net.openu.restapi.account.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openu.restapi.account.repository.Accounts;
import net.openu.restapi.account.repository.AccountsRepository;
import net.openu.restapi.account.service.AccountsDto.Create;
import net.openu.restapi.account.service.AccountsDto.Response;
import net.openu.restapi.account.service.AccountsDto.UpdateStatus;
import net.openu.restapi.api.exception.AlreadyExistsException;
import net.openu.restapi.api.exception.NotFoundException;
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
public class AccountsService {

  private final AccountsRepository accountsRepository;

  @Transactional
  public AccountsDto.Response signUp(Create create) {
    Boolean existAccount = accountsRepository.existsByUsername(create.getEmail());
    //중복체크
    if (existAccount) {
      throw new AlreadyExistsException("accounts", "email", create.getEmail());
    }

    Accounts savedAccount = accountsRepository.save(create.toEntity());

    return AccountsDto.Response.of(savedAccount);


  }

  public List<AccountsDto.Response> findAll() {
//    List<Accounts> findAllAccounts = accountsRepository.findAll();
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
}
