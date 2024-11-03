package com.DevelopersGroupINU.Images_And_Combinations.repository;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users , Long> {
    Users findByEmail(String email);
}
