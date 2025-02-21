package com.codeit.workbook.backend.service;

import com.codeit.workbook.backend.domain.quiz.Quiz;
import com.codeit.workbook.backend.domain.user.User;
import com.codeit.workbook.backend.dto.QuizRequestDto;
import com.codeit.workbook.backend.repository.QuizRepository;
import com.codeit.workbook.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    // 여러 문제 저장 (한 번에 저장)
    public void saveQuizzes(QuizRequestDto quizRequestDto) {
        String userId = quizRequestDto.getUserId();
        String subject = quizRequestDto.getSubject();
        String professor = quizRequestDto.getProfessor();

        // userId로 User 객체를 조회
        Optional<User> userOptional = userRepository.findByUserId(userId);

        User user = userOptional.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 여러 문제 저장
        for (QuizRequestDto.QuizItem item : quizRequestDto.getQuizzes()) {
            Quiz quiz = new Quiz();
            quiz.setQuestion(item.getQuestion());
            quiz.setAnswer(item.getAnswer());
            quiz.setSubject(subject);  // 교수, 과목은 동일하게 설정
            quiz.setProfessor(professor);
            quiz.setUser(user);

            quizRepository.save(quiz);  // 문제 저장
        }
    }

    // 문제 조회 (사용자 ID 기준)
    public List<Quiz> getQuizzesByUserId(String userId) {
        return quizRepository.findByUser_UserId(userId);  // userId로 필터링하여 조회
    }

    // 과목명, 교수명, 사용자 고유 ID로 문제 조회
    public List<Quiz> getQuizzesByUserIdAndSubjectAndProfessor(String userId, String subject, String professor) {
        return quizRepository.findByUser_UserIdAndSubjectAndProfessor(userId, subject, professor);
    }
}
