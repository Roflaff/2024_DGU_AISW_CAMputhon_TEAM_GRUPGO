package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rofla.back.back.model.Behavior;

import java.util.Optional;

@Repository
public interface BehaviorRepository extends JpaRepository<Behavior, String> {
    Optional<Behavior> findByUsername(String username);
}
