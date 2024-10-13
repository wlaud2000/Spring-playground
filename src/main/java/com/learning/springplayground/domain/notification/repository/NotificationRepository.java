package com.learning.springplayground.domain.notification.repository;

import com.learning.springplayground.domain.notification.entity.Noti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Noti, Long> {

}
