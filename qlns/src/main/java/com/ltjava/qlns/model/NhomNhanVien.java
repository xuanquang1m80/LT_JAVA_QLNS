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
public class NhomNhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên nhóm nhân viên không được để trống")
    String tenNhom;

    String moTa;

    @ManyToMany(mappedBy = "nhomNhanViens")
    Set<NhanVien> nhanViens;
}
