package com.ltjava.qlns.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class LoaiNhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên loại nhân viên không được trống")
    private String tenLoai;

    private String mota;

    @OneToMany(mappedBy = "loaiNhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NhanVien> nhanViens;

}
