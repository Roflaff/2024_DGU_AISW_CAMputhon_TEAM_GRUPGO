package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.model.Behavior;
import rofla.back.back.model.Order;
import rofla.back.back.repository.BehaviorRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BehaviorService {

        private final BehaviorRepository behaviorRepository;

        // 활동 등록
        public void saveBehaivor(Behavior behavior) {
            if (behaviorRepository.findByUsername(behavior.getUsername()).isPresent()) {
                throw new IllegalArgumentException("동일한 활동이 존재 합니다.");
            }
        }

        //조회
        public Optional<Behavior> searchBehaviorByUsername(String username) {
            return behaviorRepository.findByUsername(username);
        }

        //수정
        public Optional<Behavior> modifyBehavior(Behavior newbehavior) {
            return behaviorRepository.findByUsername(newbehavior.getUsername())
                    .map(Behavior -> {
                        Behavior.setUsername(newbehavior.getUsername());
                        Behavior.setUser(newbehavior.getUser());
                        Behavior.setRest(newbehavior.getRest());
                        Behavior.setMeal(newbehavior.getMeal());
                        Behavior.setStudy(newbehavior.getStudy());
                        Behavior.setExercise(newbehavior.getExercise());
                        Behavior.setHobby(newbehavior.getHobby());
                        return behaviorRepository.save(Behavior);
                    });
        }


        //삭제
        public void deleteBehavior(Behavior behavior) {
            if(behaviorRepository.findByUsername(behavior.getUsername()).isPresent()) {
                behaviorRepository.delete(behaviorRepository.findByUsername(behavior.getUsername()).get());
            }
            else {
                System.out.println("not Present in DB!");
            }
        }
    }

}
