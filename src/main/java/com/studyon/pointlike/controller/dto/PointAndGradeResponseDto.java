package com.studyon.pointlike.controller.dto;

import com.studyon.pointlike.domain.PointAndGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointAndGradeResponseDto {
    private Long id;
    private Long userId;
    private int point;
    private int todayTomatoCount;
    private String userGrade;

    public PointAndGradeResponseDto(PointAndGrade pointAndGrade) {
        this.id = pointAndGrade.getId();
        this.userId = pointAndGrade.getUserId();
        this.point = pointAndGrade.getPoint();
        this.todayTomatoCount = pointAndGrade.getTodayTomatoCount();
        this.userGrade = pointAndGrade.getUserGrade().toString();
    }
}
