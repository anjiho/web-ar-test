package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventObjectEntity;
import com.sk.ar.web.test.entity.ArEventScanningImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArEventObjectEntityRepository extends JpaRepository<ArEventObjectEntity, Integer> {
    void deleteByArEventId(int ArEventId);
    List<ArEventObjectEntity> findByArEventIdOrderByArEventObjectIdAsc(int arEventId);
}
