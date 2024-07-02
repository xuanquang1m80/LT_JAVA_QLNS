package com.ltjava.qlns.service;

import com.ltjava.qlns.model.LoaiNhanVien;
import com.ltjava.qlns.repository.LoaiNhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiNhanVienService {
    @Autowired
    private LoaiNhanVienRepository loaiNhanVienRepository;

    public List<LoaiNhanVien> getAllLoaiNhanVien(){
        return loaiNhanVienRepository.findAll();
    }

}
