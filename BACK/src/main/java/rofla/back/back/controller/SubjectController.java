package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.model.Subject;
import rofla.back.back.model.User;
import rofla.back.back.service.SubjectService;
import rofla.back.back.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Subject")
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Subject>> getAllSubject() {
        List<Subject> total = subjectService.getAllSubject();
        return ResponseEntity.ok(total);
    }

    // 데이터 조회
    @GetMapping("/search/{subjectNum}/{userName}")
    public ResponseEntity<Subject> searchSubject(@PathVariable String subjectNum, String userName){
        Optional<Subject> content = subjectService.searchSubjectBySubjectNumAndUsername(subjectNum, userName);
        return content.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @PutMapping("/update")
    public ResponseEntity<Subject> updateUser(@RequestBody Subject newSubject) {
        Optional<Subject> content = subjectService.modifySubject(newSubject);
        return content.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/{subjectNum}/{userName}")
    public ResponseEntity<String> deleteSubject(@PathVariable String subjectNum, String userName) {
        try {
            subjectService.deleteSubject(subjectNum, userName);
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
