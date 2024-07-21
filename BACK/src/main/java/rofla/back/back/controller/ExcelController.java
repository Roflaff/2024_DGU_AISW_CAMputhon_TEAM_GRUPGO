package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import rofla.back.back.service.ExcelService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/excel")
public class ExcelController {
    private final ExcelService excelService;
    @PostMapping("/subjectInfo")
    public ResponseEntity<?> readSubjectInfoExcelFile(@RequestParam("file") MultipartFile file){
        try {
            excelService.readSubjectInfoExcelFile(file);
            return ResponseEntity.ok("read SubjectInfo Excel file complete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/subject/{username}")
    public ResponseEntity<?> readSubjectExcelFile(@PathVariable("username") String username, @RequestParam("file") MultipartFile file){
        try {
            excelService.readSubjectExcelFile(file, username);
            return ResponseEntity.ok("read Subject Excel file complete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
