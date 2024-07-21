//package rofla.back.back.service;
//
//import lombok.RequiredArgsConstructor;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import rofla.back.back.model.SubjectInfo;
//import rofla.back.back.repository.SubjectInfoRepository;
//import rofla.back.back.repository.SubjectRepository;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@RequiredArgsConstructor
//@Service
//public class ExcelService {
//    private final SubjectInfoRepository subjectInfoRepository;
//    private final SubjectRepository subjectRepository;
//
//    public boolean readSubjectExcelFile(MultipartFile file) throws IOException {
//        if (file.isEmpty()) {
//            throw new IllegalArgumentException("Uploaded file is empty");
//        }
//        List<SubjectInfo> subjectInfoList = new ArrayList<>();
//        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
//            Sheet sheet = workbook.getSheetAt(0);
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) { // Skip header row
//                    continue;
//                }
//                SubjectInfo subjectInfo = new SubjectInfo();
//                for (Cell cell : row) {
//                    // 엑셀 열을 기준으로 집어넣을 항목
//                    switch (cell.getColumnIndex()) {
//                        case 0:
//                            break;
//                        case 4:
//                            subjectInfo.setSubjectNum(cell.toString());
//                            break;
//                        case 5:
//                            //
//                            subjectInfo.setName(cell.toString());
//                            break;
//                        case 8:
//                            String input = cell.toString();
//                            String[] parts = input.split(",");
//                            String startTime ="";
//                            String endTime ="";
//                            // 각 부분에서 요일과 시간을 추출
//                            for (String part : parts) {
//                                extractTimes(part,startTime,endTime);
//                            }
//                            subjectInfo.setStartTime(startTime);
//                            subjectInfo.setEndTime(endTime);
//                            break;
//                        case 9:
//                            // 과목명
//                            subjectInfo.setSubjectName(cell.toString());
//                            break;
//                        case 8:
//                            break;
//                        case 9:
//                            // 학점
//                            subjectInfo.setGrade(Integer.parseInt(cell.toString()));
//                            break;
//                    }
//                }
//                subjects.add(subject);
//            }
//        }
//        for (Subject subject : subjects) {
//            try {
//                subjectService.saveSubject(subject);
//            } catch (IllegalArgumentException e) {
//                return ResponseEntity.status(409).body(e.getMessage());
//            }
//        }
//    }
//    public void extractTimes(String input, String startTime, String endTime){
//        Pattern pattern = Pattern.compile("(\\D+)(\\d+(?:\\.\\d+)?-\\d+(?:\\.\\d+)?)/(\\d{1,2}:\\d{2})-(\\d{1,2}:\\d{2})");
//        Matcher matcher = pattern.matcher(input);
//        if(startTime != ""){
//            startTime += ",";
//            endTime += ",";
//        }
//        if (matcher.find()) {
//            String day = matcher.group(1); // 요일
//            startTime += day + matcher.group(3); // 시작 시간2
//            endTime += day + matcher.group(4); // 마감 시간2
//        }
//    }
//}
