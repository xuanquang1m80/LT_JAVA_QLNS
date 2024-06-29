package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.CongTac;
import com.ltjava.qlns.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongTacRepository extends JpaRepository<CongTac, Long> {
    List<CongTac> findByNhanVien(NhanVien nhanVien);
}
