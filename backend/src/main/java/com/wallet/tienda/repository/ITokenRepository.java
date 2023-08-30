package com.wallet.tienda.repository;

import com.wallet.tienda.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
