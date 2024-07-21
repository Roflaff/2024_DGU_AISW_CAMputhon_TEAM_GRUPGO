package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.dto.loginRequest;
import rofla.back.back.model.Food;
import rofla.back.back.model.User;
import rofla.back.back.service.UserService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody loginRequest loginRequest) {
        try {
            userService.searchUserByUsername(loginRequest.getUsername());
            return ResponseEntity.ok("login!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> total = userService.getAllUser();
        return ResponseEntity.ok(total);
    }

    // 데이터 조회
    @GetMapping("/search/{name}")
    public ResponseEntity<User> searchUser(@PathVariable String name){
        Optional<User> content = userService.searchUserByUsername(name);
        return content.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User newUser) {
        Optional<User> content = userService.modifyUser(newUser);
        return content.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteTest(@PathVariable String name) {
        try {
            userService.deleteUser(name);
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
