package com.ltjava.qlns.service;

import com.ltjava.qlns.model.NhomNhanVien;
import com.ltjava.qlns.repository.NhomNhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhomNhanVienService {

    @Autowired
    private NhomNhanVienRepository nhomNhanVienRepository;

    public List<NhomNhanVien> findAll() {
        return nhomNhanVienRepository.findAll();
    }

    public Optional<NhomNhanVien> findById(Long id) {
        return nhomNhanVienRepository.findById(id);
    }

    public NhomNhanVien save(NhomNhanVien nhomNhanVien) {
        return nhomNhanVienRepository.save(nhomNhanVien);
    }

    public void deleteById(Long id) {
        nhomNhanVienRepository.deleteById(id);
    }
}
