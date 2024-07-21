package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rofla.back.back.jwt.JWTUtil;
import rofla.back.back.model.SubjectInfo;
import rofla.back.back.repository.SubjectInfoRepository;
import rofla.back.back.repository.SubjectRepository;
import rofla.back.back.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExcelController {
    private final SubjectInfoRepository subjectInfoRepository;
    private final SubjectRepository subjectRepository;
    @PostMapping("/upload/excels")
    public ResponseEntity<?> readSubjectExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        List<SubjectInfo> subjectInfoList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                SubjectInfo subjectInfo = new SubjectInfo();
                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 4:
                            subjectInfo.setSubjectNum(cell.toString());
                            break;
                        case 5:
                            subjectInfo.setName(cell.toString());

                    }
                }
            }
        }
        return ResponseEntity.ok("good");
    }
}
