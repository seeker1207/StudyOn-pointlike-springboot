package com.studyon.pointlike.service;

import com.studyon.pointlike.controller.dto.PointAndGradeRequestDto;
import com.studyon.pointlike.controller.dto.PointAndGradeResponseDto;
import com.studyon.pointlike.domain.PointAndGrade;
import com.studyon.pointlike.domain.PointAndGradeRepository;
import com.studyon.pointlike.domain.PointValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PointAndGradeService {
    final PointAndGradeRepository pointAndGradeRepository;

    @Transactional
    public PointAndGradeResponseDto getUserPoint(Long userId) {
        PointAndGrade pointAndGrade = pointAndGradeRepository.findPointAndGradeByUserId(userId);

        return new PointAndGradeResponseDto(pointAndGrade);
    }
    @Transactional
    public PointAndGradeResponseDto makeUserPoint(PointAndGradeRequestDto pointAndGradeRequestDto) {
        PointAndGrade pointAndGrade = pointAndGradeRequestDto.toDomain();
        pointAndGradeRepository.save(pointAndGrade);

        return new PointAndGradeResponseDto(pointAndGrade);
    }

    @Transactional
    public PointAndGradeResponseDto consumePoint(PointAndGradeRequestDto pointAndGradeRequestDto) {
        PointAndGrade pointAndGrade = pointAndGradeRepository.findPointAndGradeByUserId(pointAndGradeRequestDto.getUserId());

        PointValue stateEnum = PointValue.valueOf(pointAndGradeRequestDto.getState());

        switch (stateEnum){
            case ENTER_ROOM:
                pointAndGrade.enterRoom();
                break;
            case MAKE_ROOM:
                pointAndGrade.makeRoom();
                break;
        }

        return new PointAndGradeResponseDto(pointAndGrade);
    }

    @Transactional
    public PointAndGradeResponseDto refundPoint(PointAndGradeRequestDto pointAndGradeRequestDto) {
        PointAndGrade pointAndGrade = pointAndGradeRepository.findPointAndGradeByUserId(pointAndGradeRequestDto.getUserId());

        pointAndGrade.refund(pointAndGradeRequestDto.isOwner());

        return new PointAndGradeResponseDto(pointAndGrade);
    }

}
