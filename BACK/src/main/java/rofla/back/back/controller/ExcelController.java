package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rofla.back.back.jwt.JWTUtil;
import rofla.back.back.model.SubjectInfo;
import rofla.back.back.repository.SubjectInfoRepository;
import rofla.back.back.repository.SubjectRepository;
import rofla.back.back.service.ExcelService;
import rofla.back.back.service.SubjectInfoService;
import rofla.back.back.service.SubjectService;
import rofla.back.back.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
