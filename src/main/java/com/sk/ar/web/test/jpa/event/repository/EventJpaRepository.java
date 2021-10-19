package com.sk.ar.web.test.jpa.event.repository;

import com.sk.ar.web.test.jpa.event.EventJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<EventJpa, Integer> {

}
