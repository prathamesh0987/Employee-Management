package com.psv.springboot.Repository;

import com.psv.springboot.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository {
    User findByEmail(String email);
}
