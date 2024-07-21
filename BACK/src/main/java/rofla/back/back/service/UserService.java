package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rofla.back.back.model.User;
import rofla.back.back.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    public void saveUser(User users) {
        if (userRepository.findByUsername(users.getUsername()).isPresent()) {
            throw new IllegalArgumentException("동일한 아이디가 있는 유저가 존재 합니다.");
        }
        // 암호화 후 저장
        String password =  users.getPassword();
        users.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(users);
    }

    //조회
    public Optional<TableName> searchTestByCol2Name(String c) {
        return testRepository.findByC(c);
    }

    //수정

    //삭제

}
