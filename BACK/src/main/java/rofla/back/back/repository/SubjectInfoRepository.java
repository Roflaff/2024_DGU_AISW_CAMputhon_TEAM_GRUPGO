package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.SubjectInfo;


import java.util.Optional;

@Repository
public interface SubjectInfoRepository extends JpaRepository<SubjectInfo, Long> {
    Optional<SubjectInfo> findBySubjectNum(String subjectNum);

}
