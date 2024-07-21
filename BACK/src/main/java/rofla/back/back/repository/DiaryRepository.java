package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.Diary;
import rofla.back.back.model.User;

import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    Optional<Diary> findByUsernameAndDateAndEmptyNum(User name, String date, Integer emptyNum);
}
