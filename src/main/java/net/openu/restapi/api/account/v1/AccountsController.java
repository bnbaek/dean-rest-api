package net.openu.restapi.api.account.v1;

import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.service.AccountService;
import net.openu.restapi.account.service.AccountsDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1/accounts", "/api/accounts"})
public class AccountsController {

  private final AccountService accountService;

  @PostMapping
  public AccountsDto.Response signUp(@RequestBody AccountsDto.Request request) {

    return accountService.signUp(request);


  }

}
