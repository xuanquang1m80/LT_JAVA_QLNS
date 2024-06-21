package com.ltjava.qlns.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhongBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên phòng ban không được trống")
    String tenPB;

    String moTa;

    Date ngayTao;
    Date ngaySua;

    @OneToMany(mappedBy = "phongBan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NhanVien> nhanViens;
}
