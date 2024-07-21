package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.model.Behavior;
import rofla.back.back.model.OrderedFood;
import rofla.back.back.service.BehaviorService;
import rofla.back.back.service.OrderedFoodService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Behavior")

public class BehaviorController {

    private final BehaviorService behaviorService;

    // 데이터 삽입.
    @PostMapping("/add")
    public ResponseEntity<String> addBehavior(@RequestBody Behavior behavior) {
        try {
            behaviorService.saveBehaivor(behavior);
            return ResponseEntity.ok("added successfully");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Behavior>> getAllBehavior() {
        List<Behavior> total = behaviorService.getAllBehavior();
        return ResponseEntity.ok(total);
    }

    // 데이터 조회
    @GetMapping("/search/{username}")
    public ResponseEntity<Behavior> searchBehavior(String username){
        Optional<Behavior> content = behaviorService.searchBehaviorByUsername(username);
        return content.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @PutMapping("/update")
    public ResponseEntity<Behavior> updateOrderedFood(@RequestBody Behavior newBehavior) {
        Optional<Behavior> content = behaviorService.modifyBehavior(newBehavior);
        return content.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteBehavior(String username) {
        try {
            behaviorService.deleteBehavior(username);
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
