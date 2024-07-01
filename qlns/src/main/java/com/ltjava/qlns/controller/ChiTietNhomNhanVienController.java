package com.ltjava.qlns.controller;


import com.ltjava.qlns.model.ChiTietNhomNhanVien;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.model.NhomNhanVien;
import com.ltjava.qlns.service.ChiTietNhomNhanVienService;
import com.ltjava.qlns.service.NhanVienService;
import com.ltjava.qlns.service.NhomNhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.List;

@Controller
@RequestMapping("/chitietnhomnhanvien")
public class ChiTietNhomNhanVienController {

    @Autowired
    private ChiTietNhomNhanVienService chiTietNhomNhanVienService;
    @Autowired
    private NhomNhanVienService nhomNhanVienService;
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    public String getAllNhomNhanViens(Model model) {
        model.addAttribute("chiTietNhomNhanViens", chiTietNhomNhanVienService.findAll());
        model.addAttribute("nhanViens", nhanVienService.getdAllNhanVien());
        model.addAttribute("nhomNhanViens", nhomNhanVienService.findAll());
        return "chitietnhomnhanvien/index";
    }

    @GetMapping("/export")
    public ResponseEntity<StreamingResponseBody> exportToExcel() {
        List<ChiTietNhomNhanVien> chiTietNhomNhanViens = chiTietNhomNhanVienService.findAll();

        StreamingResponseBody stream = out -> {
            try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                Sheet sheet = workbook.createSheet("ChiTietNhomNhanVien");

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] headers = {"ID", "Tên Nhân Viên", "Tên Nhóm Nhân Viên"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }

                // Populate data rows
                int rowNum = 1;
                for (ChiTietNhomNhanVien chiTiet : chiTietNhomNhanViens) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(chiTiet.getId());
                    row.createCell(1).setCellValue(chiTiet.getNhanVien().getTenNV());
                    row.createCell(2).setCellValue(chiTiet.getNhomNhanVien().getTenNhom());
                }

                workbook.write(bos);
                out.write(bos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=chitiet_nhom_nhan_vien.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(stream);
    }



    @PostMapping
    public String createNhomNhanVien(@ModelAttribute ChiTietNhomNhanVien chiTietNhomNhanVien) {
        chiTietNhomNhanVienService.save(chiTietNhomNhanVien);

        return "redirect:/chitietnhomnhanvien";
    }



    @PostMapping("/update")
    public String updateNhomNhanVien(@Valid ChiTietNhomNhanVien chiTietNhomNhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("chiTietNhomNhanViens", chiTietNhomNhanVienService.findAll());
            model.addAttribute("nhanViens", nhanVienService.getdAllNhanVien());
            model.addAttribute("nhomNhanViens", nhomNhanVienService.findAll());


            return "nhomnhanvien/index";
        }
        chiTietNhomNhanVienService.save(chiTietNhomNhanVien);
        return "redirect:/chitietnhomnhanvien";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhomNhanVien(@PathVariable Long id) {
        chiTietNhomNhanVienService.deleteById(id);
        return "redirect:/chitietnhomnhanvien";
    }
}
