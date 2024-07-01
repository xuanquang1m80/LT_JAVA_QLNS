package com.ltjava.qlns.repository;


import com.ltjava.qlns.model.NhomNhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NhomNhanVienRepository extends JpaRepository<NhomNhanVien, Long> {
}

