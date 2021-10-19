package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventWinningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArEventWinningEntityRepository extends JpaRepository<ArEventWinningEntity, Integer> {
    ArEventWinningEntity findFirstByArEventIdOrderByArEventWinningIdDesc(int eventId);
}
