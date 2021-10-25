package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventScanningImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArEventScanningImageEntityRepository extends JpaRepository<ArEventScanningImageEntity, Integer> {
    void deleteByArEventId(int arEventId);
    List<ArEventScanningImageEntity> findAllByArEventIdOrderByArEventScanningImageIdAsc(int arEventId);
}
