package rofla.back.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rofla.back.back.dto.OrderRequest;
import rofla.back.back.model.Order;
import rofla.back.back.model.Subject;
import rofla.back.back.model.SubjectInfo;
import rofla.back.back.service.OrderService;
import rofla.back.back.service.SubjectInfoService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Order")

public class OrderController {

    private final OrderService orderService;

    // 데이터 삽입.
    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        try {
            orderService.saveOrder(order);
            return ResponseEntity.ok("added successfully");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> total = orderService.getAllOrder();
        return ResponseEntity.ok(total);
    }

    // 데이터 조회
    @GetMapping("/search/OrderRequest")
    public ResponseEntity<Order> searchOrder(@RequestBody OrderRequest orderRequest){
        Optional<Order> content = orderService.searchOrderByUsernameAndDate(orderRequest.getUsername(), orderRequest.getDate());
        return content.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order newOrder) {
        Optional<Order> content = orderService.modifyOrder(newOrder);
        return content.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/OrderRequest")
    public ResponseEntity<String> deleteOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.deleteOrder(orderRequest.getUsername(), orderRequest.getDate());
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
