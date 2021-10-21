package com.sk.ar.web.test.service;

import com.sk.ar.web.test.dto.request.ApiResultObjectDto;
import com.sk.ar.web.test.dto.request.EventAttendTimeDto;
import com.sk.ar.web.test.dto.request.EventHtmlDto;
import com.sk.ar.web.test.dto.response.CategoryDto;
import com.sk.ar.web.test.entity.*;
import com.sk.ar.web.test.entity.repository.*;
import com.sk.ar.web.test.jpa.event.*;
import com.sk.ar.web.test.jpa.event.repository.*;
import com.sk.ar.web.test.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArEventService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventBaseEntityRepository eventBaseEntityRepository;

    @Autowired
    private ArEventEntityRepository arEventEntityRepository;

    @Autowired
    private ArEventButtonEntityRepository arEventButtonEntityRepository;

    @Autowired
    private ArEventObjectEntityRepository arEventObjectEntityRepository;

    @Autowired
    private ArEventLogicalEntityRepository arEventLogicalEntityRepository;

    @Autowired
    private ArEventButtonJpaRepository arEventButtonJpaRepository;

    @Autowired
    private ArEventCategoryEntityRepository arEventCategoryEntityRepository;

    @Autowired
    private ArEventLogicalJpaRepository arEventLogicalJpaRepository;

    @Autowired
    private ArEventScanningImageEntityRepository arEventScanningImageEntityRepository;

    @Autowired
    private ArEventWinningEntityRepository arEventWinningEntityRepository;

    @Autowired
    private ArEventWinningButtonEntityRepository arEventWinningButtonEntityRepository;

    @Autowired
    private ArEventAttendTimeEntityRepository arEventAttendTimeEntityRepository;

    @Autowired
    private ArEventHtmlEntityRepository arEventHtmlEntityRepository;

    @Transactional
    public String saveEventBase(WebEventBaseEntity webEventBaseEntity) {
        eventBaseEntityRepository.save(webEventBaseEntity);
        return eventBaseEntityRepository.findFirstByOrderByIdDesc().getEventId();
    }

    public WebEventBaseEntity findLastWebEventBase() {
        return eventBaseEntityRepository.findFirstByOrderByIdDesc();
    }

    public WebEventBaseEntity findEventBase(String eventId) {
        return eventBaseEntityRepository.findByEventId(eventId);
    }

    @Transactional
    public int saveEvent(ArEventEntity arEventEntity) {
        arEventEntityRepository.save(arEventEntity);
        return arEventEntityRepository.findFirstByOrderByArEventIdDesc().getArEventId();
    }

    @Transactional
    public void saveEventButton(ArEventButtonEntity arEventButtonEntity) {
        arEventButtonEntityRepository.save(arEventButtonEntity);
    }

    @Transactional
    public void saveAllArEventObject(List<ArEventObjectEntity> arEventObjectEntityList) {
        if (!arEventObjectEntityList.isEmpty()) {
            arEventObjectEntityRepository.saveAll(arEventObjectEntityList);
        }
    }

    @Transactional
    public void saveEventLogical(ArEventLogicalEntity arEventLogicalEntity) {
        arEventLogicalEntityRepository.save(arEventLogicalEntity);
    }

    public ArEventLogicalJpa findFirstByEventIdOrderByIdDesc(int eventId) {
        return arEventLogicalJpaRepository.findFirstByEventIdOrderByIdDesc(eventId);
    }

    @Transactional
    public void saveAllEventImageScanning(List<ArEventScanningImageEntity> arEventImageScanningEntityList) {
        arEventScanningImageEntityRepository.saveAll(arEventImageScanningEntityList);
    }

    @Transactional
    public int saveEventWinning(ArEventWinningEntity arEventWinningEntity) {
        arEventWinningEntityRepository.save(arEventWinningEntity);
        return arEventWinningEntity.getArEventWinningId();
    }

    public ArEventWinningEntity findEventWinningEntityByArEventId(int arEventId) {
        return arEventWinningEntityRepository.findFirstByArEventIdOrderByArEventWinningIdDesc(arEventId);
    }

    @Transactional
    public void saveAllEventWinningButton(List<ArEventWinningButtonEntity> arEventWinningButtonEntityList) {
        arEventWinningButtonEntityRepository.saveAll(arEventWinningButtonEntityList);
    }

    @Transactional
    public void saveEventWinningButton(ArEventWinningButtonEntity arEventWinningButtonEntity) {
        arEventWinningButtonEntityRepository.save(arEventWinningButtonEntity);
    }

    @Transactional
    public void saveAllEventAttendTime(int arEventId, List<ArEventAttendTimeEntity>arEventAttendTimeEntityList) {
        if (!arEventAttendTimeEntityList.isEmpty()) {
            arEventAttendTimeEntityList
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(entity -> {
                            entity.setArEventId(arEventId);
                            entity.setCreatedDate(DateUtils.returnNowDate());
                    });

            arEventAttendTimeEntityRepository.saveAll(arEventAttendTimeEntityList);
        }
    }

    @Transactional
    public void saveAllEventHtml(int arEventId, List<EventHtmlDto>eventHtmlDtoList) {
        if (!eventHtmlDtoList.isEmpty()) {
            List<ArEventHtmlEntity> arEventHtmlEntityList = convertEventHtmlDtoListToArEventHtmlEntityList(eventHtmlDtoList);
            arEventHtmlEntityList
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(entity -> {
                        entity.setArEventId(arEventId);
                        entity.setCreatedDate(DateUtils.returnNowDate());
                    });

            arEventHtmlEntityRepository.saveAll(arEventHtmlEntityList);
        }
    }

    public ApiResultObjectDto findAllEventCategory(String categoryType, String parentCode) {
        int resultCode = HttpStatus.OK.value();

        Optional<List<ArEventCategoryEntity>>parentCategoryOptional = null;
        //조건에 따른 부모의 카테고리 리스트 가져오기
        if (StringUtils.isEmpty(categoryType) && StringUtils.isEmpty(parentCode)) {
            parentCategoryOptional = arEventCategoryEntityRepository.findAllByCategoryDepth(1);
        } else if (StringUtils.isEmpty(categoryType) && !StringUtils.isEmpty(parentCode)) {
            parentCategoryOptional = arEventCategoryEntityRepository.findAllByCategoryCodeAndCategoryDepth(parentCode.toUpperCase(), 1);
        } else if (!StringUtils.isEmpty(categoryType) && StringUtils.isEmpty(parentCode)) {
            parentCategoryOptional = arEventCategoryEntityRepository.findAllByCategoryTypeAndCategoryDepth(categoryType, 1);
        } else {
            parentCategoryOptional = arEventCategoryEntityRepository.findAllByCategoryTypeAndCategoryCodeAndCategoryDepth(categoryType, parentCode.toUpperCase(), 1);
        }

        //부모의 값이 있는지 확인
        if (parentCategoryOptional.isPresent()) {
            List<CategoryDto> categoryList = new ArrayList<>();
            List<ArEventCategoryEntity> parentCategoryList = parentCategoryOptional.orElseGet(ArrayList::new);
            //부모 카테고리 리스트 foreach 시작
            parentCategoryList.forEach(parent -> {

                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setCategoryCode(parent.getCategoryCode());
                categoryDto.setCategoryName(parent.getCategoryName());
                categoryDto.setCategoryType(parent.getCategoryType());
                categoryDto.setCategoryDepth(parent.getCategoryDepth());

                //부모 카테고리 코드에 따른 자식 카테고리 코드 리스트 값 가져오기
                List<ArEventCategoryEntity> childCategoryList = arEventCategoryEntityRepository.findAllByParentCodeAndAndCategoryDepth(parent.getCategoryCode(), 2);
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


    private List<ArEventHtmlEntity> convertEventHtmlDtoListToArEventHtmlEntityList(List<EventHtmlDto>eventHtmlDtoList) {
        return eventHtmlDtoList
                .stream()
                .map(dto -> modelMapper.map(dto, ArEventHtmlEntity.class))
                .collect(Collectors.toList());

    }

}
