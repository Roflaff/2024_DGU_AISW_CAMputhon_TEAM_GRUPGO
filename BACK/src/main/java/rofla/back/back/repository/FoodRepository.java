package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rofla.back.back.model.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
