package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.model.Food;
import rofla.back.back.model.Subject;
import rofla.back.back.model.SubjectInfo;
import rofla.back.back.service.SubjectInfoService;
import rofla.back.back.service.SubjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/SubjectInfo")

public class SubjectInfoController {

    private final SubjectInfoService subjectInfoService;

    // 데이터 삽입.
    @PostMapping("/add")
    public ResponseEntity<String> addSubjectInfo(@RequestBody SubjectInfo subjectInfo) {
        try {
            subjectInfoService.saveSubjectInfo(subjectInfo);
            return ResponseEntity.ok("added successfully");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SubjectInfo>> getAllSubjectInfo() {
        List<SubjectInfo> total = subjectInfoService.getAllSubjectInfo();
        return ResponseEntity.ok(total);
    }

    // 데이터 조회
    @GetMapping("/search/{subjectNum}")
    public ResponseEntity<SubjectInfo> searchSubjectInfo(@PathVariable String subjectNum){
        Optional<SubjectInfo> content = subjectInfoService.searchSubjectInfoBySubjectNum(subjectNum);
        return content.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @PutMapping("/update")
    public ResponseEntity<SubjectInfo> updateSubjectInfo(@RequestBody SubjectInfo newSubjectInfo) {
        Optional<SubjectInfo> content = subjectInfoService.modifySubjectInfo(newSubjectInfo);
        return content.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/{subjectNum}")
    public ResponseEntity<String> deleteSubject(@PathVariable String subjectNum) {
        try {
            subjectInfoService.deleteSubjectInfo(subjectNum);
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
