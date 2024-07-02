package com.ltjava.qlns.service;

import com.ltjava.qlns.model.ChucVu;
import com.ltjava.qlns.model.LoaiNhanVien;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private LoaiNhanVienRepository loaiNhanVienRepository;

    @Autowired
    private PhongBanRepository phongBanRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Autowired
    private CongTacRepository congTacRepository;

    public List<LoaiNhanVien> findAllLoaiNhanVien() {
        return loaiNhanVienRepository.findAll();
    }
    public List<NhanVien> getAllNhanViens() {
        return nhanVienRepository.findAll();
    }
    public List<PhongBan> findAllPhongBan() {
        return phongBanRepository.findAll();
    }

    public List<ChucVu> findAllChucVu() {
        return chucVuRepository.findAll();
    }

    // Find all employees
    public List<NhanVien> findAll() {
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

        nhanVienRepository.save(existingNhanVien);
    }

    // Delete an employee by ID
    public void delete(Long id) {
        NhanVien nhanVien = nhanVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        // Kiểm tra các mối quan hệ
        if (!congTacRepository.findByNhanVien(nhanVien).isEmpty()) { // Gọi qua đối tượng
            throw new DataIntegrityViolationException("Không thể xóa nhân viên này vì đang có công tác liên quan.");
        }


        nhanVienRepository.delete(nhanVien);
    }

    public long countEmployees() {
        return nhanVienRepository.count();
    }


}