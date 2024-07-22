package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.model.Order;
import rofla.back.back.model.OrderedFood;
import rofla.back.back.model.Subject;
import rofla.back.back.repository.OrderRepository;
import rofla.back.back.repository.OrderedFoodRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class OrderedFoodService {

    private final OrderedFoodRepository orderedFoodRepository;

    // 주문 등록
    public void saveOrderedFood(OrderedFood orderedFood) {
        if (orderedFoodRepository.findByOrderId(orderedFood.getOrderId()).isPresent()) {
            throw new IllegalArgumentException("동일한 주문이 존재 합니다.");
        }
    }

    //조회
    public Optional<OrderedFood> searchOrderedFoodByOrderId(Integer orderId) {
        return orderedFoodRepository.findByOrderId(orderId);
    }

    //모두 조회
    public List<OrderedFood> getAllOrderedFood() {
        return orderedFoodRepository.findAll();
    }

    //수정
    public Optional<OrderedFood> modifyOrderedFood(OrderedFood newOrderedFood) {
        return orderedFoodRepository.findByOrderId(newOrderedFood.getOrderId())
                .map(OrderedFood -> {
                    OrderedFood.setId(newOrderedFood.getId());
                    OrderedFood.setOrderId(newOrderedFood.getOrderId());
                    OrderedFood.setFoodCnt(newOrderedFood.getFoodCnt());
                    OrderedFood.setFood(newOrderedFood.getFood());
                    return orderedFoodRepository.save(OrderedFood);
                });
    }


    //삭제
    public void deleteOrderedFood(Integer orderId) {
        if(orderedFoodRepository.findByOrderId(orderId).isPresent()) {
            orderedFoodRepository.delete(orderedFoodRepository.findByOrderId(orderId).get());
        }
        else {
            System.out.println("not Present in DB!");
        }
    }
}
