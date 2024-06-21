package com.ltjava.qlns.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    BigDecimal phuCap;
    BigDecimal tamUng;
    BigDecimal thucLanh;
    Date ngayTinhLuong;

    String moTa;
}
