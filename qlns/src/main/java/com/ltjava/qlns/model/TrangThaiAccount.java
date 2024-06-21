package com.ltjava.qlns.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class TrangThaiAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String tenTT;

    @OneToMany(mappedBy = "trangThaiAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Account> accounts;
}

