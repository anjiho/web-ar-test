package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.WebEventBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventBaseEntityRepository extends JpaRepository<WebEventBaseEntity, Integer> {
    WebEventBaseEntity findFirstByOrderByIdDesc();
    WebEventBaseEntity findByEventId(String eventId);
}
