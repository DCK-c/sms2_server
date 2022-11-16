package org.subit.sms.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subit.sms.Data.PlayList;

public interface PlayListRepo extends JpaRepository<PlayList, Integer> {
}
