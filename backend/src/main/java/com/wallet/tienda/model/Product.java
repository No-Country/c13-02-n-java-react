package com.wallet.tienda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private int stock;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "fk_user")
    private CustomerUser user;
    @ManyToOne
    @JoinColumn(name = "fk_brand")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "fk_provider")
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order order;

}