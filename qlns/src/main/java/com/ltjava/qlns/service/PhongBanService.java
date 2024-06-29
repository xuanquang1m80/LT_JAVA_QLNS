package com.ltjava.qlns.service;

import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongBanService {

    @Autowired
    private PhongBanRepository phongBanRepository;

    public List<PhongBan> getAllPhongBan() {
        return phongBanRepository.findAll();
    }
}
