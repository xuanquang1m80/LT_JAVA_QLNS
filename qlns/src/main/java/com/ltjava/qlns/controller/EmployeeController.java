package com.ltjava.qlns.controller;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/nhanviens")
public class EmployeeController {
    @Autowired
    private NhanVienService nhanVienService;

    // Lấy danh sách tất cả nhân viên
    @GetMapping
    public List<NhanVien> getAllNhanViens() {
        return nhanVienService.getAllNhanViens();
    }

    // Lấy thông tin một nhân viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<NhanVien> getNhanVienById(@PathVariable Long id) {
        Optional<NhanVien> nhanVien = nhanVienService.getNhanVienById(id);
        return nhanVien.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Thêm nhân viên mới
    @PostMapping
    public NhanVien createNhanVien(@RequestBody NhanVien nhanVien) {
        return nhanVienService.saveNhanVien(nhanVien);
    }

    // Sửa thông tin nhân viên
    @PutMapping("/{id}")
    public ResponseEntity<NhanVien> updateNhanVien(@PathVariable Long id, @RequestBody NhanVien nhanVienDetails) {
        Optional<NhanVien> nhanVienOptional = nhanVienService.getNhanVienById(id);

        if (!nhanVienOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        NhanVien nhanVien = nhanVienOptional.get();
        nhanVien.setTenNV(nhanVienDetails.getTenNV());
        nhanVien.setCCCD(nhanVienDetails.getCCCD());
        nhanVien.setNgaySinh(nhanVienDetails.getNgaySinh());
        nhanVien.setHoKhau(nhanVienDetails.getHoKhau());
        nhanVien.setSDT(nhanVienDetails.getSDT());
        nhanVien.setImage(nhanVienDetails.getImage());
        nhanVien.setGioiTinh(nhanVienDetails.getGioiTinh());
        nhanVien.setLoaiNhanVien(nhanVienDetails.getLoaiNhanVien());
        nhanVien.setPhongBan(nhanVienDetails.getPhongBan());
        nhanVien.setBangCap(nhanVienDetails.getBangCap());
        nhanVien.setChucVu(nhanVienDetails.getChucVu());
        nhanVien.setChuyenMon(nhanVienDetails.getChuyenMon());
        nhanVien.setTrangThai(nhanVienDetails.getTrangThai());
        nhanVien.setTrinhDo(nhanVienDetails.getTrinhDo());

        NhanVien updatedNhanVien = nhanVienService.saveNhanVien(nhanVien);
        return ResponseEntity.ok(updatedNhanVien);
    }

    // Xóa nhân viên theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNhanVien(@PathVariable Long id) {
        Optional<NhanVien> nhanVienOptional = nhanVienService.getNhanVienById(id);

        if (!nhanVienOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        nhanVienService.deleteNhanVien(id);
        return ResponseEntity.noContent().build();
    }
}
