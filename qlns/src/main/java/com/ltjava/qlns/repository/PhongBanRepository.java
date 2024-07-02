package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.PhongBan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Long> {
}
