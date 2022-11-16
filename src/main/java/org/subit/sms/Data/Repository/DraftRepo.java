package org.subit.sms.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.subit.sms.Data.Draft;

@Repository
public interface DraftRepo extends JpaRepository<Draft, Integer> {

}
