package com.codeit.workbook.backend.repository;


import com.codeit.workbook.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId); // userId로 변경
    boolean existsByUserId(String userId); // userId로 변경
}
