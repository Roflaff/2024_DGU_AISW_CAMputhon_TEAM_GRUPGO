package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.dto.DiaryRequest;
import rofla.back.back.model.Diary;
import rofla.back.back.model.OrderedFood;
import rofla.back.back.service.DiaryService;
import rofla.back.back.service.OrderedFoodService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Diary")

public class DiaryController {

    private final DiaryService diaryService;

    // 데이터 삽입.
    @PostMapping("/add")
    public ResponseEntity<String> addDiary(@RequestBody Diary diary) {
        try {
            diaryService.saveDiary(diary);
            return ResponseEntity.ok("added successfully");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Diary>> getAllDiary() {
        List<Diary> total = diaryService.getAllDiary();
        return ResponseEntity.ok(total);
    }
    // 데이터 조회
    @GetMapping("/search/DiaryRequest")
    public ResponseEntity<Diary> searchDiary(@RequestBody DiaryRequest diaryRequest){
        Optional<Diary> content = diaryService.searchDiaryByUsernameAndDateAndEmptyNum(diaryRequest.getUsername(), diaryRequest.getDate(), diaryRequest.getEmptyNum());
        return content.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @PutMapping("/update")
    public ResponseEntity<Diary> updateDiary(@RequestBody Diary newDiary) {
        Optional<Diary> content = diaryService.modifyDiary(newDiary);
        return content.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/DiaryRequest")
    public ResponseEntity<String> deleteDiary(@RequestBody DiaryRequest diaryRequest) {
        try {
            diaryService.deleteDiary(diaryRequest.getUsername(), diaryRequest.getDate(), diaryRequest.getEmptyNum());
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
