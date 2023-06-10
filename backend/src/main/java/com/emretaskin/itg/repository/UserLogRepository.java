package com.emretaskin.itg.repository;

import com.emretaskin.itg.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
