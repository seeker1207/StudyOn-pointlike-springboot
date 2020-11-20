package com.studyon.pointlike.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PointAndGradeRepositoryTest {
    @Autowired
    PointAndGradeRepository pointAndGradeRepository;
    PointAndGrade testPointAndGrade;

    @BeforeEach
    public void setup() {
        PointAndGrade pointAndGrade = PointAndGrade.builder()
                .userId(2l)
                .build();

        pointAndGradeRepository.save(pointAndGrade);
        this.testPointAndGrade = pointAndGrade;
    }
    @Test
    public void 유저_포인트_정보생성하기() {
        PointAndGrade pointAndGrade = PointAndGrade.builder()
                                        .userId(1l)
                                        .build();

        pointAndGradeRepository.save(pointAndGrade);

        PointAndGrade afterPointEntity = pointAndGradeRepository.findById(pointAndGrade.getId()).get();

        assertEquals(pointAndGrade.getUserId(), afterPointEntity.getUserId());
        assertEquals(PointValue.INITIAL.getPoint(), afterPointEntity.getPoint());
        assertEquals(0, afterPointEntity.getTodayTomatoCount());

    }

    @Test
    @Rollback(value = false)
    public void 방입장후_포인트_감소하기(){

        int beforePoint = testPointAndGrade.getPoint();

        testPointAndGrade.enterRoom();

        int afterPoint = pointAndGradeRepository.findById(testPointAndGrade.getId())
                .get().getPoint();

        assertEquals(beforePoint - PointValue.ENTER_ROOM.getPoint(), afterPoint);

    }


    @Test
    public void 방만든후_포인트_감소하기() {

        int beforePoint = testPointAndGrade.getPoint();

        testPointAndGrade.makeRoom();

        int afterPoint = pointAndGradeRepository.findById(testPointAndGrade.getId())
                .get().getPoint();

        assertEquals(beforePoint - PointValue.MAKE_ROOM.getPoint(), afterPoint);
    }


}