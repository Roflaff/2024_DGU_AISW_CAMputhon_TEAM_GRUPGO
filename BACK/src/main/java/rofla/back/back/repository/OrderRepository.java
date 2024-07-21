package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
