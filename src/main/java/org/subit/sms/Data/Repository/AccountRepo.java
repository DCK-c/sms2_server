package org.subit.sms.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.subit.sms.Data.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
}
