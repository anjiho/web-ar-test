package com.sk.ar.web.test.service;

import com.sk.ar.web.test.dto.request.ApiResultObjectDto;
import com.sk.ar.web.test.dto.response.CategoryDto;
import com.sk.ar.web.test.jpa.event.*;
import com.sk.ar.web.test.jpa.event.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class ArEventService {

    @Autowired
    private EventJpaRepository eventJpaRepository;

    @Autowired
    private ArEventButtonJpaRepository arEventButtonJpaRepository;

    @Autowired
    private ArEventCategoryJpaRepository arEventCategoryJpaRepository;

    @Autowired
    private ArEventLogicalJpaRepository arEventLogicalJpaRepository;

    @Autowired
    private ArEventImageScanningEntityRepository arEventImageScanningEntityRepository;

    @Autowired
    private ArEventWinningEntityRepository arEventWinningEntityRepository;

    @Autowired
    private ArEventWinningButtonEntityRepository arEventWinningButtonEntityRepository;

    @Transactional
    public int saveEvent(EventJpa eventJpa) {
        eventJpaRepository.save(eventJpa);
        return eventJpa.getEventId();
    }

    @Transactional
    public int saveEventMainButton(ArEventButtonJpa arEventButtonJpa) {
        if (arEventButtonJpa.getEventId() > 0) {
            arEventButtonJpaRepository.save(arEventButtonJpa);
        }
        return arEventButtonJpa.getId();
    }

    public void saveAllEventLogical(List<ArEventLogicalJpa> arEventLogicalJpaList) {
        if (!arEventLogicalJpaList.isEmpty()) {
            arEventLogicalJpaRepository.saveAll(arEventLogicalJpaList);
        }
    }

    public int saveEventLogical(ArEventLogicalJpa arEventLogicalJpa) {
        arEventLogicalJpaRepository.save(arEventLogicalJpa);
        return arEventLogicalJpa.getId();
    }

    public ArEventLogicalJpa findFirstByEventIdOrderByIdDesc(int eventId) {
        return arEventLogicalJpaRepository.findFirstByEventIdOrderByIdDesc(eventId);
    }

    public void saveAllEventImageScanning(List<ArEventImageScanningEntity> arEventImageScanningEntityList) {
        arEventImageScanningEntityRepository.saveAll(arEventImageScanningEntityList);
    }

    public int saveEventWinning(ArEventWinningEntity arEventWinningEntity) {
        arEventWinningEntityRepository.save(arEventWinningEntity);
        return arEventWinningEntity.getId();
    }

    public ArEventWinningEntity findEventWinningEntityByEventId(int eventId) {
        return arEventWinningEntityRepository.findFirstByEventIdOrderByIdDesc(eventId);
    }

    public void saveAllEventWinningButton(List<ArEventWinningButtonEntity> arEventWinningButtonEntityList) {
        arEventWinningButtonEntityRepository.saveAll(arEventWinningButtonEntityList);
    }

    public void saveEventWinningButton(ArEventWinningButtonEntity arEventWinningButtonEntity) {
        arEventWinningButtonEntityRepository.save(arEventWinningButtonEntity);
    }

    public ApiResultObjectDto findAllEventCategory(String categoryType, String parentCode) {
        int resultCode = HttpStatus.OK.value();

        Optional<List<ArEventCategoryJpa>>parentCategoryOptional = null;
        //조건에 따른 부모의 카테고리 리스트 가져오기
        if (StringUtils.isEmpty(categoryType) && StringUtils.isEmpty(parentCode)) {
            parentCategoryOptional = arEventCategoryJpaRepository.findAllByCategoryDepth(1);
        } else if (StringUtils.isEmpty(categoryType) && !StringUtils.isEmpty(parentCode)) {
            parentCategoryOptional = arEventCategoryJpaRepository.findAllByCategoryCodeAndCategoryDepth(parentCode.toUpperCase(), 1);
        } else if (!StringUtils.isEmpty(categoryType) && StringUtils.isEmpty(parentCode)) {
            parentCategoryOptional = arEventCategoryJpaRepository.findAllByCategoryTypeAndCategoryDepth(categoryType, 1);
        } else {
            parentCategoryOptional = arEventCategoryJpaRepository.findAllByCategoryTypeAndCategoryCodeAndCategoryDepth(categoryType, parentCode.toUpperCase(), 1);
        }

        //부모의 값이 있는지 확인
        if (parentCategoryOptional.isPresent()) {
            List<CategoryDto> categoryList = new ArrayList<>();
            List<ArEventCategoryJpa> parentCategoryList = parentCategoryOptional.orElseGet(ArrayList::new);
            //부모 카테고리 리스트 foreach 시작
            parentCategoryList.forEach(parent -> {

                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setCategoryCode(parent.getCategoryCode());
                categoryDto.setCategoryName(parent.getCategoryName());
                categoryDto.setCategoryType(parent.getCategoryType());
                categoryDto.setCategoryDepth(parent.getCategoryDepth());

                //부모 카테고리 코드에 따른 자식 카테고리 코드 리스트 값 가져오기
                List<ArEventCategoryJpa> childCategoryList = arEventCategoryJpaRepository.findAllByParentCodeAndAndCategoryDepth(parent.getCategoryCode(), 2);
                List<Map<String, Object>> childCategoryMapList = new ArrayList<>();

                //자식의 카테고리 리스트 foreach 시작
                childCategoryList.forEach(child -> {
                    Map<String, Object> childCategoryMap = new HashMap<>();
                    childCategoryMap.put("categoryCode", child.getCategoryCode());
                    childCategoryMap.put("categoryName", child.getCategoryName());
                    childCategoryMap.put("categoryValue", child.getCategoryValue());
                    childCategoryMap.put("categoryDepth", child.getCategoryDepth());

                    childCategoryMapList.add(childCategoryMap);

                });
                //자식의 카테고리 리스트 foreach 끝
                categoryDto.setChildCategoryList(childCategoryMapList);

                categoryList.add(categoryDto);
            });
            //부모 카테고리 리스트 foreach 끝
            return new ApiResultObjectDto(categoryList, resultCode);
        }
        return new ApiResultObjectDto();
    }
}
