package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.EventBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBaseEntityRepository extends JpaRepository<EventBaseEntity, Integer> {
    EventBaseEntity findFirstByOrderByEventIdDesc();
}
