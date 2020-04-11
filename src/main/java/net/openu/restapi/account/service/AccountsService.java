package net.openu.restapi.account.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.repository.Accounts;
import net.openu.restapi.account.repository.AccountsRepository;
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
  public AccountsDto.Response signUp(AccountsDto.Request request) {
    Boolean existAccount = accountsRepository.existsByUsername(request.getEmail());
    //중복체크
    if (existAccount) {
      throw new AlreadyExistsException("accounts", "email", request.getEmail());
    }

    Accounts savedAccount = accountsRepository.save(request.toEntity());

    return AccountsDto.Response.of(savedAccount);


  }

}
