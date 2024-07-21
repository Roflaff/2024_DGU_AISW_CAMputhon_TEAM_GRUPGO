package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.Subject;
import rofla.back.back.model.SubjectInfo;
import rofla.back.back.model.User;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    Optional<Subject> findBySubjectNumAndUsername(SubjectInfo subjectNum, User username);

}
