package com.ltjava.qlns.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BangCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên bằng cấp không được để trống")
    String tenBC;

    String moTa;

    @OneToMany(mappedBy = "bangCap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NhanVien> nhanViens;
}
