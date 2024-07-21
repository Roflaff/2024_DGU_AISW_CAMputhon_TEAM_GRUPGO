package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rofla.back.back.service.ExcelService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/excel")
public class ExcelController {
    private final ExcelService excelService;
    @PostMapping("/subjectInfo")
    public ResponseEntity<?> readSubjectExcelFile(@RequestParam("file") MultipartFile file){
        try {
            excelService.readSubjectExcelFile(file);
            return ResponseEntity.ok("read SubjectInfo Excel file complete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
