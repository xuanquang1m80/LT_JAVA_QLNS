package com.ltjava.qlns.service;

import com.ltjava.qlns.exception.ResourceNotFoundException;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.model.NhomNhanVien;
import com.ltjava.qlns.repository.NhanVienRepository;
import com.ltjava.qlns.repository.NhomNhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhomNhanVienService {

    @Autowired
    private NhomNhanVienRepository nhomNhanVienRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhomNhanVien> getAllNhomNhanViens() {
        return nhomNhanVienRepository.findAll();
    }

    public NhomNhanVien getNhomNhanVienById(Long id) {
        return nhomNhanVienRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NhomNhanVien not found with id: " + id));
    }

    public NhomNhanVien createNhomNhanVien(NhomNhanVien nhomNhanVien) {
        return nhomNhanVienRepository.save(nhomNhanVien);
    }

    public NhomNhanVien updateNhomNhanVien(Long id, NhomNhanVien nhomNhanVienDetails) {
        NhomNhanVien nhomNhanVien = getNhomNhanVienById(id);
        nhomNhanVien.setTenNhom(nhomNhanVienDetails.getTenNhom());
        nhomNhanVien.setMoTa(nhomNhanVienDetails.getMoTa());
        return nhomNhanVienRepository.save(nhomNhanVien);
    }

    public void deleteNhomNhanVien(Long id) {
        NhomNhanVien nhomNhanVien = getNhomNhanVienById(id);
        for (NhanVien nhanVien : nhomNhanVien.getNhanViens()) {
            nhanVien.getNhomNhanViens().remove(nhomNhanVien);
            nhanVienRepository.save(nhanVien);
        }
        nhomNhanVienRepository.delete(nhomNhanVien);
    }
}

