package com.studyon.pointlike.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.awt.*;

@Getter
@NoArgsConstructor
@Entity
public class PointAndGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int point = PointValue.INITIAL.getPoint();

    @Column(nullable = false)
    private int todayTomatoCount = 0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGrade userGrade = UserGrade.NORMAL;

    @Builder
    public PointAndGrade(Long userId) {
        this.userId = userId;
    }

    public void gradeUp() {
        this.userGrade = UserGrade.STAR;
    }

    public void enterRoom() {
        this.point -= PointValue.ENTER_ROOM.getPoint();
    }

    public void makeRoom(){
        this.point -= PointValue.MAKE_ROOM.getPoint();
    }

    public void refund(boolean owner) { // 방장인지 아닌지에 따라 포인트를 환급한다.
        if (owner) this.point += PointValue.MAKE_ROOM.getPoint();
        else this.point += PointValue.ENTER_ROOM.getPoint();
    }
}
