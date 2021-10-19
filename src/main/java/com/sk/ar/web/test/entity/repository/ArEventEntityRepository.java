package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventEntity;
import com.sk.ar.web.test.entity.ArEventLogicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArEventEntityRepository extends JpaRepository<ArEventEntity, Integer> {
    ArEventEntity findFirstByOrderByArEventIdDesc();
}
