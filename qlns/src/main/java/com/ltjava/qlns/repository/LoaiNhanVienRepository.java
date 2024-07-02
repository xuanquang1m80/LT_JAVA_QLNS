package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.LoaiNhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiNhanVienRepository extends JpaRepository<LoaiNhanVien, Long> {
}
