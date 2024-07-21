package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {

}
