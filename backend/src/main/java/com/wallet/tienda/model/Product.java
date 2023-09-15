package com.wallet.tienda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JoinColumn(name = "fk_brand")
    @JsonIgnoreProperties("products")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    @JsonIgnoreProperties("products")
    private Category category;

}
