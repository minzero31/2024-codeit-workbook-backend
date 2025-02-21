package com.codeit.workbook.backend.repository;

import com.codeit.workbook.backend.domain.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // 사용자 고유 ID로 문제 조회 (User 객체의 userId를 기준으로)
    List<Quiz> findByUser_UserId(String userId);

    // 과목명, 교수명, 사용자 고유 ID로 문제 조회
    List<Quiz> findByUser_UserIdAndSubjectAndProfessor(String userId, String subject, String professor);
}
