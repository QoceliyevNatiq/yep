package com.ecommerce.yep.repo;

import com.ecommerce.yep.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);

    Optional<User> findByFullName(String fullName);

    boolean existsByEmail(String email);
    boolean existsByFullName(String fullName);


}
