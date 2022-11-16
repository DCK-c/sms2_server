package org.subit.sms.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subit.sms.data.Screen;

public interface ScreenRepo extends JpaRepository<Screen, Integer> {
}
