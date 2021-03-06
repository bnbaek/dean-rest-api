package net.openu.restapi.account.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

  Optional<Accounts> findByUsername(String username);

  Optional<Accounts> findByUuid(String uuid);
  Optional<Accounts> findByProviderAndProviderId(String provider, Long id);

  Boolean existsByUsername(String email);
}
