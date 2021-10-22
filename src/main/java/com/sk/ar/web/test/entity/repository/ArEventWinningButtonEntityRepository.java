package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventWinningButtonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArEventWinningButtonEntityRepository extends JpaRepository<ArEventWinningButtonEntity, Integer> {
    void deleteByArEventWinningIdIn(List<Integer>arEventWinningIdList);
}
