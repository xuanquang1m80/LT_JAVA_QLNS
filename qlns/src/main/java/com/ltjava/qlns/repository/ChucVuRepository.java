package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Long> {
}
