package rofla.back.back.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.Dto.TimeTableDTO;
import rofla.back.back.model.Subject;
import rofla.back.back.model.SubjectInfo;
import rofla.back.back.model.User;
import rofla.back.back.repository.SubjectInfoRepository;
import rofla.back.back.repository.SubjectRepository;
import rofla.back.back.repository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    // 수업 생성
    public void saveSubject(Subject subject) {
        if (subjectRepository.findBySubjectNumAndUsername(subject.getSubjectNum(), subject.getUsername()).isPresent()) {
            throw new IllegalArgumentException("동일한 수업이 존재 합니다.");
        }
    }

    //조회
    public Optional<Subject> searchSubjectBySubjectNumAndUsername(Subject subject) {
        return subjectRepository.findBySubjectNumAndUsername(subject.getSubjectNum(), subject.getUsername());
    }

    //수정
    public Optional<Subject> modifySubject(Subject newSubject) {
        return subjectRepository.findBySubjectNumAndUsername(newSubject.getSubjectNum(), newSubject.getUsername())
                .map(Subject -> {
                    Subject.setUsername(newSubject.getUsername());
                    Subject.setSubjectNum(newSubject.getSubjectNum());
                    return subjectRepository.save(Subject);
                });
    }

    public void deleteSubject(Subject subject) {
        if(subjectRepository.findBySubjectNumAndUsername(subject.getSubjectNum(), subject.getUsername()).isPresent()) {
            subjectRepository.delete(subjectRepository.findBySubjectNumAndUsername(subject.getSubjectNum(), subject.getUsername()).get());
        }
        else {
            System.out.println("not Present in DB!");
        }
    }

    public List<TimeTableDTO> sendAllSubjectTime(String username){
        List<Subject> subjectList = subjectRepository.findAllByUsername(userRepository.findByUsername(username).orElseThrow(()->new EntityNotFoundException("username is unavailable")));
        System.out.println(subjectList);
        List<SubjectInfo> subjectInfoList = new ArrayList<>();
        for(Subject subject: subjectList){
            subjectInfoList.add(subject.getSubjectNum());
        }
        List<TimeTableDTO> timeTableDTOList = new ArrayList<>();
        for(SubjectInfo subjectInfo: subjectInfoList){
            String[] startSplit = subjectInfo.getStartTime().split(",");
            String[] endSplit = subjectInfo.getEndTime().split(",");
            for(int i=0;i< startSplit.length;i++){
                TimeTableDTO timeTableDTO = new TimeTableDTO();
                timeTableDTO.setSubjectNum(subjectInfo.getSubjectNum());
                timeTableDTO.setSubjectName(subjectInfo.getName());
                timeTableDTO.setProfessor(subjectInfo.getProfessor());
                System.out.println(startSplit[i].charAt(0));
                switch (startSplit[i].charAt(0)){
                    case '1':
                        timeTableDTO.setDayOfWeek("월");
                        break;
                    case '2':
                        timeTableDTO.setDayOfWeek("화");
                        break;
                    case '3':
                        timeTableDTO.setDayOfWeek("수");
                        break;
                    case '4':
                        timeTableDTO.setDayOfWeek("목");
                        break;
                    case '5':
                        timeTableDTO.setDayOfWeek("금");
                        break;
                }
                timeTableDTO.setStartTime(startSplit[i]);
                timeTableDTO.setEndTime(endSplit[i]);
                timeTableDTOList.add(timeTableDTO);
            }
        }
        timeTableDTOList.sort(Comparator.comparingDouble(as->Double.parseDouble(as.getStartTime())));
        for(TimeTableDTO timeTableDTO:timeTableDTOList){
            String startTime = timeTableDTO.getStartTime();
            String endTime = timeTableDTO.getEndTime();
            timeTableDTO.setStartTime(startTime.substring(1));
            timeTableDTO.setEndTime(endTime.substring(1));
        }
        return timeTableDTOList;
    }
}
