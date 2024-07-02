package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.TrinhDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrinhDoRepository extends JpaRepository<TrinhDo, Long> {
}
