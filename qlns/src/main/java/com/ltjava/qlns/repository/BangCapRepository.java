package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.BangCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BangCapRepository extends JpaRepository<BangCap, Long> {
}
