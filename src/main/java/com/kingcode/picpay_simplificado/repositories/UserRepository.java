package com.kingcode.picpay_simplificado.repositories;

import com.kingcode.picpay_simplificado.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);
}
