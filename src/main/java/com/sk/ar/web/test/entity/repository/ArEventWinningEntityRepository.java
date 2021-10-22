package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventWinningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArEventWinningEntityRepository extends JpaRepository<ArEventWinningEntity, Integer> {
    List<ArEventWinningEntity> findAllByArEventId(int arEventId);
    ArEventWinningEntity findFirstByArEventIdOrderByArEventWinningIdDesc(int eventId);
    void deleteByArEventId(int arEventId);
}
