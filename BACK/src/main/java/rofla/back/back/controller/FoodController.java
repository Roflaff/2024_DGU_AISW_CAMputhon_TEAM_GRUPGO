package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.model.Food;
import rofla.back.back.service.FoodService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Food")
public class FoodController {
    private final FoodService foodService;

    // 데이터 삽입.
    @PostMapping("/add")
    public ResponseEntity<String> addTest(@RequestBody Food food) {
        try {
            foodService.saveFood(food);
            return ResponseEntity.ok("added successfully");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // 데이터 조회
    @GetMapping("/search/{foodname}")
    public ResponseEntity<Food> searchTest(@PathVariable String foodname){
        Optional<Food> content = foodService.searchFoodByName(foodname);
        return content.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Food>> getAllFood() {
        List<Food> total = foodService.getAllFood();
        return ResponseEntity.ok(total);
    }

    @PutMapping("/update")
    public ResponseEntity<Food> updateTest(@RequestBody Food newfood) {
        Optional<Food> t1 = foodService.modifyFood(newfood);
        return t1.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteTest(@PathVariable String name) {
        try {
            foodService.deleteFood(name);
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}

