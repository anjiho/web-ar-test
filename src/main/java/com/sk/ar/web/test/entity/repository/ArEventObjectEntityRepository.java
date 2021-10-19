package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventObjectEntity;
import com.sk.ar.web.test.entity.ArEventScanningImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArEventObjectEntityRepository extends JpaRepository<ArEventObjectEntity, Integer> {
}
