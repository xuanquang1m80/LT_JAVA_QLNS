package com.ltjava.qlns.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CongTac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Date ngayBD;
    Date ngayKT;

    @NotBlank(message = "Địa điểm công tác bắt buộc có")
    String diaDiemCT;
    @NotBlank(message = "Mục đích công tác phải có")
    String mucDich;

    Date ngayTao;

    @ManyToOne
    @JoinColumn(name = "IDNhanVien")
    NhanVien nhanVien;
}
