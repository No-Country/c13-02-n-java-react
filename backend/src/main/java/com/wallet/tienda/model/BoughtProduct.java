package com.wallet.tienda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bought_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoughtProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "fk_product")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "fk_buy")
    private Buy buy;

}
