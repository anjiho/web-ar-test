package com.sk.ar.web.test.jpa.event.repository;

import com.sk.ar.web.test.jpa.event.ArEventLogicalJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArEventLogicalJpaRepository extends JpaRepository<ArEventLogicalJpa, Integer> {
}
