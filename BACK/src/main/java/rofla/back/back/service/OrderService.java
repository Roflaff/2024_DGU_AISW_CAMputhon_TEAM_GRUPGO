package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.model.Order;
import rofla.back.back.repository.OrderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // 주문 등록
    public void saveOrder(Order order) {
        if (orderRepository.findByUsernameAndDate(order.getUsername(), order.getDate()).isPresent()) {
            throw new IllegalArgumentException("동일한 주문이 존재 합니다.");
        }
    }

    //조회
    public Optional<Order> searchOrderByUsernameAndDate(Order order) {
        return orderRepository.findByUsernameAndDate(order.getUsername(), order.getDate());
    }

    //수정
    public Optional<Order> modifyOrder(Order newOrder) {
        return orderRepository.findByUsernameAndDate(newOrder.getUsername(), newOrder.getDate())
                .map(Order -> {
                    Order.setUsername(newOrder.getUsername());
                    Order.setDate(newOrder.getDate());
                    return orderRepository.save(Order);
                });
    }


    //삭제
    public void deleteOrder(Order order) {
        if(orderRepository.findByUsernameAndDate(order.getUsername(), order.getDate()).isPresent()) {
            orderRepository.delete(orderRepository.findByUsernameAndDate(order.getUsername(), order.getDate()).get());
        }
        else {
            System.out.println("not Present in DB!");
        }
    }
}
