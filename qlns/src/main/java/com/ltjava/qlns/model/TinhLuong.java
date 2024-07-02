package com.ltjava.qlns.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date ngayTinhLuong;

    String moTa;

    @ManyToOne
    @JoinColumn(name = "IDNhanVien")
    NhanVien nhanVien;
}
