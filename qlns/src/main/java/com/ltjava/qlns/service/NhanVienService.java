package com.ltjava.qlns.service;

import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllNhanViens() {
        return nhanVienRepository.findAll();
    }

    public Optional<NhanVien> getNhanVienById(Long id) {
        return nhanVienRepository.findById(id);
    }
}
