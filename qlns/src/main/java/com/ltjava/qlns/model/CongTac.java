package com.ltjava.qlns.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CongTac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date ngayBD;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date ngayKT;

    @NotBlank(message = "Địa điểm công tác bắt buộc có")
    String diaDiemCT;
    @NotBlank(message = "Mục đích công tác phải có")
    String mucDich;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date ngayTao;

    @ManyToOne
    @JoinColumn(name = "IDNhanVien")
    NhanVien nhanVien;
}
