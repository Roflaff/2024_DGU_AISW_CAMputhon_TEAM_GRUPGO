package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.model.Order;
import rofla.back.back.model.OrderedFood;
import rofla.back.back.service.OrderService;
import rofla.back.back.service.OrderedFoodService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/OrderedFood")

public class OrderedFoodController {

    private final OrderedFoodService orderedFoodService;

    // 데이터 삽입.
    @PostMapping("/add")
    public ResponseEntity<String> addOrderedFood(@RequestBody OrderedFood orderedFood) {
        try {
            orderedFoodService.saveOrderedFood(orderedFood);
            return ResponseEntity.ok("added successfully");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderedFood>> getAllOrderedFood() {
        List<OrderedFood> total = orderedFoodService.getAllOrderedFood();
        return ResponseEntity.ok(total);
    }

    // 데이터 조회
    @GetMapping("/search/{orderId}")
    public ResponseEntity<OrderedFood> searchOrderedFood(Integer orderId){
        Optional<OrderedFood> content = orderedFoodService.searchOrderedFoodByOrderId(orderId);
        return content.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @PutMapping("/update")
    public ResponseEntity<OrderedFood> updateOrderedFood(@RequestBody OrderedFood newOrderedfood) {
        Optional<OrderedFood> content = orderedFoodService.modifyOrderedFood(newOrderedfood);
        return content.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrderedFood(Integer orderId) {
        try {
            orderedFoodService.deleteOrderedFood(orderId);
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
