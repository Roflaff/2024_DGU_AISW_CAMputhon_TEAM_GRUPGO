//package rofla.back.back.service;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import rofla.back.back.model.Subject;
//import rofla.back.back.model.SubjectInfo;
//import rofla.back.back.repository.SubjectRepository;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class SubjectService {
//    private final SubjectRepository subjectRepository;
//
//    // 수업 생성
//    public void saveSubjectInfo(Subject subject) {
//        if (subjectRepository.findBySubject(subjectInfo.getSubjectNum()).isPresent()) {
//            throw new IllegalArgumentException("동일한 수업이 존재 합니다.");
//        }
//    }
//
//    //조회
//    public Optional<SubjectInfo> searchSubjectInfoBySubjectNum(String SubjectNum) {
//        return subjectInfoRepository.findBySubjectNum(SubjectNum);
//    }
//
//    //수정
//    public Optional<SubjectInfo> modifySubjectInfo(SubjectInfo newSubjectInfo) {
//        return subjectInfoRepository.findBySubjectNum(newSubjectInfo.getSubjectNum())
//                .map(SubjectInfo -> {
//                    SubjectInfo.setId(newSubjectInfo.getId());
//                    SubjectInfo.setName(newSubjectInfo.getName());
//                    SubjectInfo.setSubjectNum(newSubjectInfo.getSubjectNum());
//                    SubjectInfo.setStartTime(newSubjectInfo.getStartTime());
//                    SubjectInfo.setEndTime(newSubjectInfo.getEndTime());
//                    SubjectInfo.setClassroom(newSubjectInfo.getClassroom());
//                    SubjectInfo.setProfessor(newSubjectInfo.getProfessor());
//                    return subjectInfoRepository.save(SubjectInfo);
//                });
//    }
//
//
//    //삭제
//    public void deleteSubjectInfo(String SubjectNum) {
//        if(subjectInfoRepository.findBySubjectNum(SubjectNum).isPresent()) {
//            subjectInfoRepository.delete(subjectInfoRepository.findBySubjectNum(SubjectNum).get());
//        }
//        else {
//            System.out.println("not Present in DB!");
//        }
//    }
//}
