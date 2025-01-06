package com.codeit.workbook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class UserResponseDto implements Serializable {
    private boolean isSuccessed; // 성공 여부
    private String message;      // 메시지
    private String userName;     // 사용자 이름 반환용 (회원가입 성공 시)
    private String code;         // 응답 코드 (로그인 시 사용)
}