package com.wallet.tienda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
    @CreationTimestamp
    private LocalDateTime purchaseDate;
    @OneToMany
    private List<BoughtProduct> purchasedProducts;
    @ManyToOne
    @JoinColumn(name = "fk_provider")
    private Provider provider;
}
