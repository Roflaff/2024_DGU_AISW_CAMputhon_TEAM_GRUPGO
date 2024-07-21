package rofla.back.back.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.model.Subject;
import rofla.back.back.repository.SubjectRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

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


    //삭제
    public void deleteSubject(Subject subject) {
        if(subjectRepository.findBySubjectNumAndUsername(subject.getSubjectNum(), subject.getUsername()).isPresent()) {
            subjectRepository.delete(subjectRepository.findBySubjectNumAndUsername(subject.getSubjectNum(), subject.getUsername()).get());
        }
        else {
            System.out.println("not Present in DB!");
        }
    }

    public void find
}
