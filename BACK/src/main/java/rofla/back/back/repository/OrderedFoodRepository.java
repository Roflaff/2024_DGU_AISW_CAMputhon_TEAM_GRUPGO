package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rofla.back.back.model.Order;
import rofla.back.back.model.OrderedFood;

public interface OrderedFoodRepository extends JpaRepository<OrderedFood, Integer> {
}
