package com.ltjava.qlns.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TinhLuong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal luongThang;
    Integer ngayCong;
    Integer ngayNghi;
    BigDecimal phuCap;
    BigDecimal tamUng;
    BigDecimal thucLanh;
    Date ngayTinhLuong;

    String moTa;
    @ManyToOne
    @JoinColumn(name = "IDNhanVien")
    NhanVien nhanVien;
}
