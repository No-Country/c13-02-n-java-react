package com.wallet.tienda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "providers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable( name = "provider_user",
    joinColumns = @JoinColumn (name = "id_provider"),
    inverseJoinColumns = @JoinColumn (name = "id_user"))
    private List<CustomerUser> users;

}
