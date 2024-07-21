package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
}
