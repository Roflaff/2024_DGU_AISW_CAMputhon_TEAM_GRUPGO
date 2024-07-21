package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rofla.back.back.model.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {
}
