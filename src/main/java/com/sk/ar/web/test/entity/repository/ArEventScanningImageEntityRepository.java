package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventScanningImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArEventScanningImageEntityRepository extends JpaRepository<ArEventScanningImageEntity, Integer> {
    void deleteByArEventId(int arEventId);
}
