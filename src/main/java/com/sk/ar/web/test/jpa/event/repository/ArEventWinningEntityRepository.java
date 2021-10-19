package com.sk.ar.web.test.jpa.event.repository;

import com.sk.ar.web.test.jpa.event.ArEventWinningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArEventWinningEntityRepository extends JpaRepository<ArEventWinningEntity, Integer> {
    ArEventWinningEntity findFirstByEventIdOrderByIdDesc(int eventId);

}
