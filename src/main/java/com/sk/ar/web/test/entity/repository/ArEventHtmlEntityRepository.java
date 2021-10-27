package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventHtmlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArEventHtmlEntityRepository extends JpaRepository<ArEventHtmlEntity, Integer> {
    List<ArEventHtmlEntity> findAllByArEventIdOrderByHtmlTypeSortAsc(int arEventId);
    void deleteByArEventId(int arEventId);
}
