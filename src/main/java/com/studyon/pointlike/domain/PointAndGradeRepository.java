package com.studyon.pointlike.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PointAndGradeRepository extends JpaRepository<PointAndGrade, Long> {
    PointAndGrade findPointAndGradeByUserId(Long userId);
}
