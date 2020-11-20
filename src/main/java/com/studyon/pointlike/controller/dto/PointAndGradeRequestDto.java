package com.studyon.pointlike.controller.dto;

import com.studyon.pointlike.domain.PointAndGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointAndGradeRequestDto {
    private Long userId;
    private String state;
    private boolean owner;

    public PointAndGrade toDomain() {
        return PointAndGrade.builder()
                .userId(userId)
                .build();
    }

}
