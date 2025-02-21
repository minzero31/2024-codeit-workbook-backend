package com.codeit.workbook.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizRequestDto {

    private String userId;    // 사용자 ID
    private String subject;    // 과목명
    private String professor;  // 교수명
    private List<QuizItem> quizzes;  // 문제 리스트

    @Getter
    @Setter
    public static class QuizItem {
        private String question;  // 문제 내용
        private String answer;    // 정답
    }
}
