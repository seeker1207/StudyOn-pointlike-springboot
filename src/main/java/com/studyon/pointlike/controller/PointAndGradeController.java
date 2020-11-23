package com.studyon.pointlike.controller;

import com.studyon.pointlike.controller.dto.PointAndGradeRequestDto;
import com.studyon.pointlike.controller.dto.PointAndGradeResponseDto;
import com.studyon.pointlike.service.PointAndGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PointAndGradeController {
    final private PointAndGradeService pointAndGradeService;

    @GetMapping("/point-grade")
    public PointAndGradeResponseDto getUserPoint(@RequestParam("userId") Long userId) {

        return pointAndGradeService.getUserPoint(userId);
    }

    @PostMapping("/point-grade")
    public PointAndGradeResponseDto initialUserPoint(@RequestBody PointAndGradeRequestDto pointAndGradeRequestDto) {

        return pointAndGradeService.makeUserPoint(pointAndGradeRequestDto);
    }

    @PutMapping("/point-grade")
    public PointAndGradeResponseDto modifyUserPoint(@RequestBody PointAndGradeRequestDto pointAndGradeRequestDto) {
        String stateString = pointAndGradeRequestDto.getState().replaceAll("\\B(?=[A-Z])","_");
        pointAndGradeRequestDto.setState(stateString.toUpperCase());

        if (UserState.valueOf(pointAndGradeRequestDto.getState()) == UserState.REFUND){
            return pointAndGradeService.refundPoint(pointAndGradeRequestDto);
        }
        else {
            return pointAndGradeService.consumePoint(pointAndGradeRequestDto);
        }

    }


}
