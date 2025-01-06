package com.codeit.workbook.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String userId;  // 학번
    private String userName; // 사용자 이름 (회원가입 시만 사용)
    private String password;  // 비밀번호
}