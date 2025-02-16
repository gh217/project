package com.example.app.repo;

import com.example.app.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    public Optional<Users> findByEmail(String gmail);

    Page<Users> findAll(Pageable pageable);
}
