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
    public Optional<User> searchUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //수정
    public Optional<User> modifyUser(User newUser) {
        return userRepository.findByUsername(newUser.getUsername())
                .map(User -> {
                    User.setId(newUser.getId());
                    User.setName(newUser.getName());
                    User.setUsername(newUser.getUsername());
                    User.setPassword(newUser.getPassword());
                    User.setPhoneNum(newUser.getPhoneNum());
                    User.setMajor(newUser.getMajor());
                    User.setRole(newUser.getRole());
                    return userRepository.save(User);
                });
    }


    //삭제
    public void deleteUser(String username) {
        if(userRepository.findByUsername(username).isPresent()) {
            userRepository.delete(userRepository.findByUsername(username).get());
        }
        else {
            System.out.println("not Present in DB!");
        }
    }
}
