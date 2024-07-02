package com.ltjava.qlns.service;

import com.ltjava.qlns.model.ChucVu;
import com.ltjava.qlns.model.LoaiNhanVien;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.repository.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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


    public ByteArrayInputStream exportNhanVienListToExcel(List<NhanVien> nhanVienList) throws IOException {
        String[] columns = {"ID", "Tên", "CCCD", "Ngày Sinh", "Hộ Khẩu", "Số Điện Thoại"};
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("NhanVien");

            // Tạo header row
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
            }

            // Tạo các hàng dữ liệu
            int rowIdx = 1;
            for (NhanVien nhanVien : nhanVienList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(nhanVien.getId());
                row.createCell(1).setCellValue(nhanVien.getTenNV());
                row.createCell(2).setCellValue(nhanVien.getCCCD());
                row.createCell(3).setCellValue(nhanVien.getNgaySinh().toString());
                row.createCell(4).setCellValue(nhanVien.getHoKhau());
                row.createCell(5).setCellValue(nhanVien.getSDT());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}