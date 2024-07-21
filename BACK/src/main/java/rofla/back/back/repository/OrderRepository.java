package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rofla.back.back.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
