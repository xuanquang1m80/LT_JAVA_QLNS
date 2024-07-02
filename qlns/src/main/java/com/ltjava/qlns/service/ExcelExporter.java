package com.ltjava.qlns.service;

import com.ltjava.qlns.model.NhanVien;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public static ByteArrayInputStream export(List<NhanVien> nhanViens) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Nhan Viens");
            Row headerRow = sheet.createRow(0);

            // Header row
            String[] headers = {"ID", "Họ tên", "CCCD", "Ngày sinh", "Hộ khẩu", "Số điện thoại", "Giới tính", "Loại nhân viên", "Phòng ban", "Bằng cấp", "Chức vụ", "Chuyên môn", "Trạng thái", "Trình độ"};
            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

            // Data rows
            int rowIdx = 1;
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

            for (NhanVien nhanVien : nhanViens) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(nhanVien.getId());
                row.createCell(1).setCellValue(nhanVien.getTenNV());
                row.createCell(2).setCellValue(nhanVien.getCCCD());

                Cell ngaySinhCell = row.createCell(3);
                ngaySinhCell.setCellValue(nhanVien.getNgaySinh());
                ngaySinhCell.setCellStyle(dateCellStyle);

                row.createCell(4).setCellValue(nhanVien.getHoKhau());
                row.createCell(5).setCellValue(nhanVien.getSDT());
                row.createCell(6).setCellValue(nhanVien.getGioiTinh().toString());
                row.createCell(7).setCellValue(nhanVien.getLoaiNhanVien() != null ? nhanVien.getLoaiNhanVien().getTenLoai() : "");
                row.createCell(8).setCellValue(nhanVien.getPhongBan() != null ? nhanVien.getPhongBan().getTenPB() : "");
                row.createCell(9).setCellValue(nhanVien.getBangCap() != null ? nhanVien.getBangCap().getTenBC() : "");
                row.createCell(10).setCellValue(nhanVien.getChucVu() != null ? nhanVien.getChucVu().getTenChucVu() : "");
                row.createCell(11).setCellValue(nhanVien.getChuyenMon() != null ? nhanVien.getChuyenMon().getTenCM() : "");
                row.createCell(12).setCellValue(nhanVien.getTrangThai() != null ? nhanVien.getTrangThai().getTenTT() : "");
                row.createCell(13).setCellValue(nhanVien.getTrinhDo() != null ? nhanVien.getTrinhDo().getTenTD() : "");
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
