package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.dto.SubjectRequest;
import rofla.back.back.model.Subject;
import rofla.back.back.model.SubjectInfo;
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

    // 데이터 삽입.
    @PostMapping("/add")
    public ResponseEntity<String> addSubject(@RequestBody Subject Subject) {
        try {
            subjectService.saveSubject(Subject);
            return ResponseEntity.ok("added successfully");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Subject>> getAllSubject() {
        List<Subject> total = subjectService.getAllSubject();
        return ResponseEntity.ok(total);
    }

    // 데이터 조회
    @GetMapping("/search/SubjectRequest")
    public ResponseEntity<Subject> searchSubject(@RequestBody SubjectRequest subjectRequest){
        Optional<Subject> content = subjectService.searchSubjectBySubjectNumAndUsername(subjectRequest.getSubjectNum(), subjectRequest.getUserName());
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
    @DeleteMapping("/delete/SubjectRequest")
    public ResponseEntity<String> deleteSubject(@RequestBody SubjectRequest subjectRequest) {
        try {
            subjectService.deleteSubject(subjectRequest.getSubjectNum(), subjectRequest.getUserName());
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
