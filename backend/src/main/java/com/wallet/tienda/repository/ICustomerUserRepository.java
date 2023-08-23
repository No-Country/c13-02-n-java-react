package com.wallet.tienda.repository;

import com.wallet.tienda.model.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerUserRepository extends JpaRepository<CustomerUser, Long> {
}
