package com.ltjava.qlns.service;

import com.ltjava.qlns.model.ChiTietNhomNhanVien;
import com.ltjava.qlns.repository.ChiTietNhomNhanVienRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietNhomNhanVienService {

    @Autowired
    private ChiTietNhomNhanVienRespository chiTietNhomNhanVienRespository;

    public List<ChiTietNhomNhanVien> findAll() {
        return chiTietNhomNhanVienRespository.findAll();
    }

    public Optional<ChiTietNhomNhanVien> findById(Long id) {
        return chiTietNhomNhanVienRespository.findById(id);
    }

    public ChiTietNhomNhanVien save(ChiTietNhomNhanVien nhomNhanVien) {
        return chiTietNhomNhanVienRespository.save(nhomNhanVien);
    }

    public void deleteById(Long id) {
        chiTietNhomNhanVienRespository.deleteById(id);
    }
}
