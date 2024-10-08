package rofla.back.back.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rofla.back.back.model.Subject;
import rofla.back.back.model.SubjectInfo;
import rofla.back.back.repository.SubjectInfoRepository;
import rofla.back.back.repository.SubjectRepository;
import rofla.back.back.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class ExcelService {
    private final UserRepository userRepository;
    private final SubjectInfoRepository subjectInfoRepository;
    private final SubjectRepository subjectRepository;

    public void readSubjectInfoExcelFile(MultipartFile file) throws Exception{
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                SubjectInfo subjectInfo = new SubjectInfo();
                for (Cell cell : row) {
                    // 엑셀 열을 기준으로 집어넣을 항목
                    switch (cell.getColumnIndex()) {
                        case 0:
                            break;
                        case 4:
                            subjectInfo.setSubjectNum(cell.toString());
                            break;
                        case 5:
                            //
                            subjectInfo.setName(cell.toString());
                            break;
                        case 6:
                            //
                            subjectInfo.setProfessor(cell.toString());
                            break;
                        case 8:
                            String input = cell.toString();
                            String[] parts = input.split(",");
                            StringBuilder startTime = new StringBuilder();
                            StringBuilder endTime = new StringBuilder();
                            // 각 부분에서 요일과 시간을 추출
                            for (String part : parts) {
                                extractTimes(part,startTime,endTime);
                            }
                            subjectInfo.setStartTime(startTime.toString());
                            subjectInfo.setEndTime(endTime.toString());
                            break;
                        case 9:
                            // 과목명
                            subjectInfo.setClassroom(cell.toString());
                            break;
                    }
                }
                subjectInfoRepository.save(subjectInfo);
            }
        } catch(Exception e){
            throw e;
        }
    }
    public void readSubjectExcelFile(MultipartFile file,String username) throws Exception{
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                Subject subject = new Subject();
                subject.setUsername(userRepository.findByUsername(username).orElseThrow(()->new EntityNotFoundException("username is not found")));
                for (Cell cell : row) {
                    // 엑셀 열을 기준으로 집어넣을 항목
                    switch (cell.getColumnIndex()) {
                        case 0:
                            break;
                        case 4:
                            subject.setSubjectNum(subjectInfoRepository.findBySubjectNum(cell.toString()).orElseThrow(
                                    ()->new EntityNotFoundException("subjectNum is not found")));
                            break;
                    }
                }
                subjectRepository.save(subject);
            }
        } catch(Exception e){
            throw e;
        }
    }
    public void extractTimes(String input, StringBuilder startTime, StringBuilder endTime){
        Pattern pattern = Pattern.compile("(\\D+)(\\d+(?:\\.\\d+)?-\\d+(?:\\.\\d+)?)/(\\d{1,2}:\\d{2})-(\\d{1,2}:\\d{2})");
        Matcher matcher = pattern.matcher(input);
        if(!startTime.isEmpty()){
            startTime.append(",");
            endTime.append(",");
        }
        if (matcher.find()) {
            String day = matcher.group(1); // 요일
            switch (day){
                case "월":
                    day="1";
                    break;
                case "화":
                    day="2";
                    break;
                case "수":
                    day="3";
                    break;
                case "목":
                    day="4";
                    break;
                case "금":
                    day="5";
                    break;
                case "토":
                    day="6";
                    break;
                case "일":
                    day="7";
                    break;
            }
            startTime.append(day).append(matcher.group(2).split("-")[0]); // 시작 교시
            endTime.append(day).append(matcher.group(2).split("-")[1]); // 마감 교시
        }
    }
}
