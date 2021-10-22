package com.sk.ar.web.test.entity.repository;

import com.sk.ar.web.test.entity.ArEventAttendTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.Date;

public interface ArEventAttendTimeEntityRepository extends JpaRepository<ArEventAttendTimeEntity, Integer> {
    void deleteByArEventId(int arEventId);
}
