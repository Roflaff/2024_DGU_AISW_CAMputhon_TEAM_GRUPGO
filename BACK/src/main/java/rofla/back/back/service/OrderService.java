package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.model.Subject;
import rofla.back.back.repository.OrderRepository;
import rofla.back.back.repository.SubjectRepository;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

}
