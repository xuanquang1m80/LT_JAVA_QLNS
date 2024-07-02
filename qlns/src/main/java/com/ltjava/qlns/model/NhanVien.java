package com.ltjava.qlns.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
//@RequiredArgsConstructor
//@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên nhân viên phải có")
    String tenNV;

    @Column(name = "cccd", length = 15, unique = true)
    @Length(min = 10, max = 15, message = "CCCD phải có 10 kí tự trở lên")
    @Pattern(regexp = "^[0-9]*$", message = "CCCD là bắt buộc")
    String CCCD;

    Date ngaySinh;
    String hoKhau;

    @Column(name = "phone", length = 10, unique = true)
    @Length(min = 10, max = 10, message = "Số điện thoại phải 10 chữ số")
    @Pattern(regexp = "^[0-9]*$", message = "Số điện thoại phải là số")
    String SDT;

    String image;

    @Enumerated(EnumType.STRING)
     GioiTinh gioiTinh;

    @ManyToOne
    @JoinColumn(name = "IDLoaiNV")
     LoaiNhanVien loaiNhanVien;

    @ManyToOne
    @JoinColumn(name = "IDPhongBan")
    @JsonBackReference
    PhongBan phongBan;

    @ManyToOne
    @JoinColumn(name = "IDBangCap")
    BangCap bangCap;

    @ManyToOne
    @JoinColumn(name = "IDChucVu")
    @JsonBackReference
    ChucVu chucVu;

    @ManyToOne
    @JoinColumn(name = "IDChuyenMon")
    ChuyenMon chuyenMon;

    @ManyToOne
    @JoinColumn(name = "IDTrangThai")
    TrangThai trangThai;

    @ManyToOne
    @JoinColumn(name = "IDTrinhDo")
    TrinhDo trinhDo;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CongTac> congTacs;


    @ManyToMany
    @JoinTable(
            name = "CT_NhomNV",
            joinColumns = @JoinColumn(name = "IDNhanVien"),
            inverseJoinColumns = @JoinColumn(name = "IDNhomNV")
    )
    Set<NhomNhanVien> nhomNhanViens;


    @OneToOne(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @ToString.Exclude
    private Account account;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TinhLuong> tinhLuongs;
}
