package com.codeit.workbook.backend.service;

import com.codeit.workbook.backend.domain.user.User;
import com.codeit.workbook.backend.repository.UserRepository;
import com.codeit.workbook.backend.dto.UserRequestDto;
import com.codeit.workbook.backend.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto register(UserRequestDto requestDto) {
        if (userRepository.existsByUserId(requestDto.getUserId())) {
            return new UserResponseDto(false, "회원가입 실패. 학번 또는 입장 코드를 확인해주세요.", null, null);
        }

        User user = User.builder()
                .userId(requestDto.getUserId())
                .userName(requestDto.getUserName())
                .password(requestDto.getPassword()) // 비밀번호는 암호화 없이 저장
                .build();
        userRepository.save(user);

        return new UserResponseDto(true, "회원가입 성공", user.getUsername(), null);
    }

    public UserResponseDto login(UserRequestDto requestDto) {
        Optional<User> userOptional = userRepository.findByUserId(requestDto.getUserId());

        if (!userOptional.isPresent()) {
            return new UserResponseDto(false, "로그인 실패. 학번과 비밀번호를 확인해주세요.", null, "login fail");
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(requestDto.getPassword())) {
            return new UserResponseDto(false, "로그인 실패. 학번과 비밀번호를 확인해주세요.", null, "login fail");
        }

        return new UserResponseDto(true, "로그인 성공", null, "login success");
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
