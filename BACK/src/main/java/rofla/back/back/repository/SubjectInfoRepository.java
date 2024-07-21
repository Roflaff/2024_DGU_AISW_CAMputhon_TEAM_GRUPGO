package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectInfoRepository extends JpaRepository<SubjectInfo, Long> {

}
