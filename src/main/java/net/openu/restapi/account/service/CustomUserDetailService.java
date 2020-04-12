package net.openu.restapi.account.service;

import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.repository.AccountsRepository;
import net.openu.restapi.api.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by iopenu@gmail.com on 2020/04/12
 * Github : https://github.com/bnbaek
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final AccountsRepository accountsRepository;

  @Override
  public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
    return accountsRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(uuid));
  }
}
