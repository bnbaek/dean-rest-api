package net.openu.restapi.account.service;

import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.repository.Accounts;
import net.openu.restapi.account.repository.AccountsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

  private final AccountsRepository accountsRepository;

  @Transactional
  public AccountsDto.Response signUp(AccountsDto.Request request) {
    //TODO 중복체크
    Accounts savedAccount = accountsRepository.save(request.toEntity());

    return AccountsDto.Response.of(savedAccount);


  }

}
