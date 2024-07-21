package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.model.Diary;
import rofla.back.back.model.Order;
import rofla.back.back.model.User;
import rofla.back.back.repository.DiaryRepository;
import rofla.back.back.repository.OrderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class DiaryService {

        private final DiaryRepository diaryRepository;

        // 메모 등록
        public void saveDiary(Diary diary) {
            if (diaryRepository.findByUsernameAndDateAndEmptyNum(diary.getUsername(), diary.getDate(), diary.getEmptyNum()).isPresent()) {
                throw new IllegalArgumentException("동일한 메모가 존재 합니다.");
            }
        }

        //조회
        public Optional<Diary> searchDiaryByUsernameAndDateAndEmptyNum(Diary diary) {
            return diaryRepository.findByUsernameAndDateAndEmptyNum(diary.getUsername(), diary.getDate(), diary.getEmptyNum());
        }

        //수정
        public Optional<Diary> modifyDiary(Diary newDiary) {
            return diaryRepository.findByUsernameAndDateAndEmptyNum(newDiary.getUsername(), newDiary.getDate(), newDiary.getEmptyNum())
                    .map(Diary -> {
                        Diary.setId(newDiary.getId());
                        Diary.setUsername(newDiary.getUsername());
                        Diary.setDate(newDiary.getDate());
                        Diary.setBody(newDiary.getBody());
                        Diary.setEmptyNum(newDiary.getEmptyNum());
                        return diaryRepository.save(Diary);
                    });
        }


        //삭제
        public void deleteDiary(Diary diary) {
            if (diaryRepository.findByUsernameAndDateAndEmptyNum(diary.getUsername(), diary.getDate(), diary.getEmptyNum()).isPresent()){
                diaryRepository.delete(diaryRepository.findByUsernameAndDateAndEmptyNum(diary.getUsername(), diary.getDate(), diary.getEmptyNum()).get());
            }
            else {
                System.out.println("not Present in DB!");
            }
        }
}