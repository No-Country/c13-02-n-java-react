package com.wallet.tienda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String photoUrl;
    private String businessName;
    @ManyToOne
    @JoinColumn(name = "fk_role")
    private Role role;

}
