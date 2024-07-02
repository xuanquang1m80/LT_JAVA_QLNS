package com.ltjava.qlns.repository;
import com.ltjava.qlns.model.ChiTietNhomNhanVien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietNhomNhanVienRespository extends JpaRepository<ChiTietNhomNhanVien ,Long > {
}
