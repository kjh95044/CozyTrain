package com.ssafy.cozytrain.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkListId;
    private Date checkListDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
