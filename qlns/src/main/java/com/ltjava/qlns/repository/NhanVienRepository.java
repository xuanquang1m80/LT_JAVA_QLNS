package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    Optional<NhanVien> findByCCCD(String CCCD);
}
