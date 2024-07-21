package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.model.User;
import rofla.back.back.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    // POST :  [/User/join] 유저 추가 (학번 중복 확인)
    @PostMapping("/join")
    public ResponseEntity<String> saveUser(@RequestBody User users) {
        try {
            userService.saveUser(users);
            return ResponseEntity.ok("회원가입 성공!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

}
