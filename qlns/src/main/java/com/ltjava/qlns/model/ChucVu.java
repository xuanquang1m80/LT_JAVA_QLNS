package com.ltjava.qlns.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên chức vụ không được trống")
    String tenChucVu;

    @DecimalMin(value = "0.0", inclusive = false, message = "Lương phải lớn hơn 0")
    @Digits(integer = 10, fraction = 2, message = "Lương không hợp lệ")
    @Column(nullable = false)
    BigDecimal Luong;

    String moTa;

    Date ngayTao;
    Date ngaySua;

    @OneToMany(mappedBy = "chucVu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NhanVien> nhanViens;

}
