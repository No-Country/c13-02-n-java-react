package com.wallet.tienda.repository;

import com.wallet.tienda.model.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerUserRepository extends JpaRepository<CustomerUser, Long> {
    Optional<CustomerUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
