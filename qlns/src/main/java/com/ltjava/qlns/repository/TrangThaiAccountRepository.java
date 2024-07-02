package com.ltjava.qlns.repository;


import com.ltjava.qlns.model.TrangThaiAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiAccountRepository extends JpaRepository<TrangThaiAccount, Long> {
}
