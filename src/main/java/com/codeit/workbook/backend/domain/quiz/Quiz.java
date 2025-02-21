package com.codeit.workbook.backend.domain.quiz;

import com.codeit.workbook.backend.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
    private String subject;
    private String professor;

    @ManyToOne
    @JsonBackReference  // 순환 참조를 방지하기 위해 추가
    private User user;
}
