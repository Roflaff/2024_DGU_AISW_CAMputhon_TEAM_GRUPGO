package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
}
