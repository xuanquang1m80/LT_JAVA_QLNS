package com.ltjava.qlns.service;

import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    // Find all employees
    public List<NhanVien> getdAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    // Find an employee by ID
    public NhanVien findById(Long id) {
        return nhanVienRepository.findById(id).orElseThrow(() -> new RuntimeException("NhanVien not found"));
    }

    // Save a new employee
    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);
    }

    // Update an existing employee
    public void update(Long id, NhanVien nhanVien) {
        NhanVien existingNhanVien = nhanVienRepository.findById(id).orElseThrow(() -> new RuntimeException("NhanVien not found"));
        existingNhanVien.setTenNV(nhanVien.getTenNV());
        existingNhanVien.setCCCD(nhanVien.getCCCD());
        existingNhanVien.setNgaySinh(nhanVien.getNgaySinh());
        existingNhanVien.setHoKhau(nhanVien.getHoKhau());
        existingNhanVien.setSDT(nhanVien.getSDT());
        existingNhanVien.setImage(nhanVien.getImage());
        existingNhanVien.setGioiTinh(nhanVien.getGioiTinh());
        existingNhanVien.setLoaiNhanVien(nhanVien.getLoaiNhanVien());
        existingNhanVien.setPhongBan(nhanVien.getPhongBan());
        existingNhanVien.setBangCap(nhanVien.getBangCap());
        existingNhanVien.setChucVu(nhanVien.getChucVu());
        existingNhanVien.setChuyenMon(nhanVien.getChuyenMon());
        existingNhanVien.setTrangThai(nhanVien.getTrangThai());
        existingNhanVien.setTrinhDo(nhanVien.getTrinhDo());
        existingNhanVien.setCongTacs(nhanVien.getCongTacs());
        existingNhanVien.setNhomNhanViens(nhanVien.getNhomNhanViens());
        existingNhanVien.setAccount(nhanVien.getAccount());
        nhanVienRepository.save(existingNhanVien);
    }

    // Delete an employee by ID
    public void delete(Long id) {
        nhanVienRepository.deleteById(id);
    }
}