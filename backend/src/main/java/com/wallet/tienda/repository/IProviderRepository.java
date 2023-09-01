package com.wallet.tienda.repository;

import com.wallet.tienda.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProviderRepository extends JpaRepository<Provider, Long> {

    boolean existsByName(String name);
}
