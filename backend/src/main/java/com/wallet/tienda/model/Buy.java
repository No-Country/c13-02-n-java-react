package com.wallet.tienda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buys")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "fk_provider")
    private Provider provider;
}
