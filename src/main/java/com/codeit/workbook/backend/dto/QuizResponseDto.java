package com.codeit.workbook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizResponseDto {
    private boolean isSuccessed;
    private String message;
}
