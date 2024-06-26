package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.CongTac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongTacRepository extends JpaRepository<CongTac, Long> {
}
