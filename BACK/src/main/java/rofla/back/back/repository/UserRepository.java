package rofla.back.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rofla.back.back.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
