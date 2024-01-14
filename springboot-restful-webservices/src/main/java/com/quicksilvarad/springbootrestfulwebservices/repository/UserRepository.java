package com.quicksilvarad.springbootrestfulwebservices.repository;
import com.quicksilvarad.springbootrestfulwebservices.DTO.UserDTO;
import com.quicksilvarad.springbootrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


     // User JPA uses APIs to create derived methods based on the naming conventions.
    // here the findByEmail method uses findBy predicate to predict what can be found and the keyword after the predicate
    // informs the API to generate derived method to find resource using Email
     Optional<User> findByEmail(String email);
}
