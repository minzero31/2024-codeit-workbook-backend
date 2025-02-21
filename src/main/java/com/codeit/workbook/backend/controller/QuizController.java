package com.codeit.workbook.backend.controller;

import com.codeit.workbook.backend.domain.quiz.Quiz;
import com.codeit.workbook.backend.dto.QuizRequestDto;
import com.codeit.workbook.backend.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // 문제 저장 API
    @PostMapping("/bulk")
    public ResponseEntity<String> saveQuizzes(@RequestBody QuizRequestDto quizRequestDto) {
        try {
            quizService.saveQuizzes(quizRequestDto);
            return ResponseEntity.ok("문제들이 성공적으로 저장되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 사용자 ID로 문제 조회 API
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Quiz>> getQuizzesByUserId(@PathVariable String userId) {
        List<Quiz> quizzes = quizService.getQuizzesByUserId(userId);
        return ResponseEntity.ok(quizzes);
    }

    // 과목명, 교수명, 사용자 고유 ID로 문제 조회 API
    @GetMapping("/user/{userId}/subject/{subject}/professor/{professor}")
    public ResponseEntity<List<Quiz>> getQuizzesByUserIdAndSubjectAndProfessor(
            @PathVariable String userId,
            @PathVariable String subject,
            @PathVariable String professor) {
        List<Quiz> quizzes = quizService.getQuizzesByUserIdAndSubjectAndProfessor(userId, subject, professor);
        return ResponseEntity.ok(quizzes);
    }
}
