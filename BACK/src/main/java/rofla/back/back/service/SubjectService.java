package rofla.back.back.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.model.Subject;
import rofla.back.back.model.SubjectInfo;
import rofla.back.back.model.User;
import rofla.back.back.repository.SubjectInfoRepository;
import rofla.back.back.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectInfoRepository subjectInfoRepository;

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

    public void findEmptyTime(User user){
        List<Subject> subjectList = subjectRepository.findAllByUsername(user);
        List<SubjectInfo> subjectInfoList = new ArrayList<>();
        for(Subject subject: subjectList){
            subjectInfoList.add(subject.getSubjectNum());
        }
        for(int i=0;i<subjectInfoList.size()-1;i++){
            for(int j=0;j<subjectInfoList.size()-i-1;j++){
                if(Integer.parseInt(subjectInfoList.get(j).getStartTime()) > Integer.parseInt(subjectInfoList.get(j+1).getStartTime())){

                }
            }
        }
        //subjectInfoRepository.findBySubjectNum()
    }
}
