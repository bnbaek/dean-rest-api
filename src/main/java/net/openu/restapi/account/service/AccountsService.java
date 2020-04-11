package net.openu.restapi.account.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.repository.Accounts;
import net.openu.restapi.account.repository.AccountsRepository;
import net.openu.restapi.account.service.AccountsDto.Create;
import net.openu.restapi.api.exception.AlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
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
}
