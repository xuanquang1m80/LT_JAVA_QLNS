package com.ltjava.qlns.service;

import com.ltjava.qlns.model.TrangThai;
import com.ltjava.qlns.repository.TrangThaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrangThaiService {

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    public List<TrangThai> getAllTrangThai() {
        return trangThaiRepository.findAll();
    }
}
