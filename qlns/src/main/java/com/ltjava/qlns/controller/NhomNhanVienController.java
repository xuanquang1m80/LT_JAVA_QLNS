package com.ltjava.qlns.controller;


import com.ltjava.qlns.model.NhomNhanVien;
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
@RequestMapping("/nhomnhanvien")
public class NhomNhanVienController {

    @Autowired
    private NhomNhanVienService nhomNhanVienService;

    @GetMapping
    public String getAllNhomNhanViens(Model model) {
        model.addAttribute("nhomNhanViens", nhomNhanVienService.findAll());
        return "nhomnhanvien/index";
    }

    @GetMapping("/export")
    public ResponseEntity<StreamingResponseBody> exportToExcel() {
        List<NhomNhanVien> nhomNhanViens = nhomNhanVienService.findAll();

        StreamingResponseBody stream = out -> {
            try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                Sheet sheet = workbook.createSheet("NhomNhanVien");

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] headers = {"ID", "Tên Nhóm", "Mô Tả"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }

                // Create data rows
                int rowNum = 1;
                for (NhomNhanVien nhomNhanVien : nhomNhanViens) {
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(nhomNhanVien.getId());
                    row.createCell(1).setCellValue(nhomNhanVien.getTenNhom());
                    row.createCell(2).setCellValue(nhomNhanVien.getMoTa());
                }

                workbook.write(bos);
                out.write(bos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=nhomnhanvien.xlsx")
                .body(stream);
    }



    @PostMapping
    public String createNhomNhanVien(@ModelAttribute NhomNhanVien nhomNhanVien) {
        nhomNhanVienService.save(nhomNhanVien);
        return "redirect:/nhomnhanvien";
    }



    @PostMapping("/update")
    public String updateNhomNhanVien(@Valid NhomNhanVien nhomNhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nhomNhanViens", nhomNhanVienService.findAll());


            return "nhomnhanvien/index";
        }
        nhomNhanVienService.save(nhomNhanVien);
        return "redirect:/nhomnhanvien";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhomNhanVien(@PathVariable Long id) {
        nhomNhanVienService.deleteById(id);
        return "redirect:/nhomnhanvien";
    }
}
