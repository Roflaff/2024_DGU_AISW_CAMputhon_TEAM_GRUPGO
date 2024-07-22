package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import rofla.back.back.service.ExcelService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Excel")
public class ExcelController {
    private final ExcelService excelService;
    @PostMapping("/subjectInfo") // DB 구축을 위한 종합강의시간표목록 엑셀 데이터 저장
    public ResponseEntity<?> readSubjectInfoExcelFile(@RequestParam("file") MultipartFile file){
        try {
            excelService.readSubjectInfoExcelFile(file);
            return ResponseEntity.ok("read SubjectInfo Excel file complete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/subject/{username}") // 사용자별 수강 시간표가 저장된 엑셀 데이터를 저장
    public ResponseEntity<?> readSubjectExcelFile(@PathVariable("username") String username, @RequestParam("file") MultipartFile file){
        try {
            excelService.readSubjectExcelFile(file, username);
            return ResponseEntity.ok("read Subject Excel file complete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
