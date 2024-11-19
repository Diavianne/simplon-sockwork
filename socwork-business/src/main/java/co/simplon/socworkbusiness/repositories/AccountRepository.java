package co.simplon.socworkbusiness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.socworkbusiness.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
