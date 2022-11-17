package org.subit.sms.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.subit.sms.data.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Account findAccountByUsernameAndDeletedIsFalse(String username);
}
