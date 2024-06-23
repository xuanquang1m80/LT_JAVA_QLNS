package com.ltjava.qlns.repository;


import com.ltjava.qlns.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUsername(String username);

    /*boolean existByEmail(String email);*/

   /* boolean existBySDT(String SDT);*/
}
