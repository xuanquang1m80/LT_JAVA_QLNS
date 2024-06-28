package com.ltjava.qlns.service;


import com.ltjava.qlns.model.Account;
import com.ltjava.qlns.model.TrangThaiAccount;
import com.ltjava.qlns.repository.TrangThaiAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TrangThaiAccountService {

    @Autowired
    private TrangThaiAccountRepository trangThaiAccountRepository;

    public List<TrangThaiAccount> getAllTrangThaiAccounts() {
        return trangThaiAccountRepository.findAll();
    }
}
