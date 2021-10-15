package com.sk.ar.web.test.jpa.event.repository;

import com.sk.ar.web.test.jpa.event.ArEventCategoryJpa;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArEventCategoryJpaRepository extends JpaRepository<ArEventCategoryJpa, Integer> {
    Optional<List<ArEventCategoryJpa>>findAllByCategoryDepth(int categoryDepth);
    Optional<List<ArEventCategoryJpa>>findAllByCategoryCodeAndCategoryDepth(String parentCode, int categoryDepth);
    Optional<List<ArEventCategoryJpa>>findAllByCategoryTypeAndCategoryDepth(String categoryType, int categoryDepth);
    Optional<List<ArEventCategoryJpa>>findAllByCategoryTypeAndCategoryCodeAndCategoryDepth(String categoryType, String parentCode, int categoryDepth);
    List<ArEventCategoryJpa>findAllByParentCodeAndAndCategoryDepth(String parentCode, int categoryDepth);
}
