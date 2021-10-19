package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventLogicalEntity;
import com.sk.ar.web.test.entity.ArEventObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArEventLogicalEntityRepository extends JpaRepository<ArEventLogicalEntity, Integer> {
}
