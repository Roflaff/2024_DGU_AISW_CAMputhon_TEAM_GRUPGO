package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.model.Order;
import rofla.back.back.model.Subject;
import rofla.back.back.repository.OrderRepository;
import rofla.back.back.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

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

    public Optional<Order> searchOrderByUsernameAndDate(String username, String date) {
        return orderRepository.findByUsernameAndDate(userRepository.findByUsername(username).get(), date);
    }

    //모두 조회
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
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

    public void deleteOrder(String username, String date) {
        if(orderRepository.findByUsernameAndDate(userRepository.findByUsername(username).get(), date).isPresent()) {
            orderRepository.delete(orderRepository.findByUsernameAndDate(userRepository.findByUsername(username).get(), date).get());
        }
        else {
            System.out.println("not Present in DB!");
        }
    }
}
