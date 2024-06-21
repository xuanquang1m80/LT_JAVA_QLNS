package com.ltjava.qlns.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrangThai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên trạng thái không được trống")
    String tenTT;

    String moTa;

    @OneToMany(mappedBy = "trangThai", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<NhanVien> nhanViens;
}
