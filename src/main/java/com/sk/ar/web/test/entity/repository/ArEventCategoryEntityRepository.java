package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArEventCategoryEntityRepository extends JpaRepository<ArEventCategoryEntity, Integer> {
    Optional<List<ArEventCategoryEntity>> findAllByCategoryDepth(int categoryDepth);
    Optional<List<ArEventCategoryEntity>>findAllByCategoryCodeAndCategoryDepth(String parentCode, int categoryDepth);
    Optional<List<ArEventCategoryEntity>>findAllByCategoryTypeAndCategoryDepth(String categoryType, int categoryDepth);
    Optional<List<ArEventCategoryEntity>>findAllByCategoryTypeAndCategoryCodeAndCategoryDepth(String categoryType, String parentCode, int categoryDepth);
    List<ArEventCategoryEntity>findAllByParentCodeAndAndCategoryDepth(String parentCode, int categoryDepth);
    Optional<ArEventCategoryEntity>findByCategoryCode(String categoryCode);
}
