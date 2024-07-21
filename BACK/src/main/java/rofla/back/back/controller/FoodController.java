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

    @GetMapping("/getAll")
    public ResponseEntity<List<Food>> getAllFood() {
        List<Food> total = foodService.getAllFood();
        return ResponseEntity.ok(total);
    }

}

    /*// 데이터 삽입.
    @PostMapping("/add") //post로 get하겠다는것. 해당 주소를 통해 맞는 요청 오면, 아래 addTest 실행
    public ResponseEntity<String> addTest(@RequestBody TableName test) {
        //TableName클래스형태에 맞춰야함 그래서, JSON으로 포스트맨에서 localhost:8080/test/add로 보냄. 이게 Rest API.
        try {   //saveTest메서드로 저장 시도. 성공 시, 문자열반환. 오류 시, 오류메시지 반환
            testService.saveTest(test);
            return ResponseEntity.ok("added successfully");
        }  catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // 데이터 조회 (1) 검색할 열로 해당 객체(모델)을 반환
    //@PathVariable을 통해 '@GetMapping("/search/{c}")'에서 정보를 받겠다는 뜻.
    @GetMapping("/search/{c}")
    public ResponseEntity<TableName> searchTest(@PathVariable String c){
        //여기서는 ResponseEntity형식을 TableName으로해서 JSON타입으로 반환받음.
        Optional<TableName> test = testService.searchTestByCol2Name(c);
        return test.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    // 데이터 조회 (2) (모든 내용 반환)
    //List를 사용하여, 한 칸에 한 튜플의 정보들을 합쳐서, 배열로 반환.
    @GetMapping("/getAll")
    public ResponseEntity<List<TableName>> getAllTest() {
        List<TableName> test = testService.getAllTest();
        return ResponseEntity.ok(test);
    }

    // 데이터 수정. PUT 요청.
    @PutMapping("/update")
    public ResponseEntity<TableName> updateTest(@RequestBody TableName newtest) {
        Optional<TableName> t1 = testService.updateTest(newtest);
        return t1.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //b의 값 찾아서, 해당 행을 삭제,
    @DeleteMapping("/delete/{b}")
    public ResponseEntity<String> deleteTest(@PathVariable int b) {
        try {
            testService.deleteTest(b);
            return ResponseEntity.ok("delete!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}*/