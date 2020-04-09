package net.openu.restapi.account.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AccountsRepositoryTest {

  @Autowired
  AccountsRepository accountsRepository;

  @Test
  void di() {}

  @Test
  void signUp() {
    String username = "abc@openu.net";
    String name = "김새로이";

    Accounts account = Accounts.builder()
        .username(username)
        .name(name)
        .build();

    Accounts abc = accountsRepository.save(account);

    System.out.println("abc = " + abc.toString());

    assertThat(abc).isNotNull();
    assertThat(abc.getId()).isEqualTo(1L);
    assertThat(abc.getUsername()).isEqualTo(username);
    assertThat(abc.getName()).isEqualTo(name);
  }

}