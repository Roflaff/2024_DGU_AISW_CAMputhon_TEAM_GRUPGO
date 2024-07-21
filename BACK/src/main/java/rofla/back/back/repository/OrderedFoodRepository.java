package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.Order;
import rofla.back.back.model.OrderedFood;

import java.util.Optional;

@Repository
public interface OrderedFoodRepository extends JpaRepository<OrderedFood, Integer> {
    Optional<OrderedFood> findByOrderId(Integer orderId);
}
