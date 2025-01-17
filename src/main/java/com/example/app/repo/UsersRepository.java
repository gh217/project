package com.example.app.repo;

import com.example.app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    public Optional<Users> findByEmail(String gmail);
}
