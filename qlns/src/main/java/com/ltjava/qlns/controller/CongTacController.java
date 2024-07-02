////package com.ltjava.qlns.controller;
////
////import com.ltjava.qlns.model.CongTac;
////import com.ltjava.qlns.model.NhanVien;
////import com.ltjava.qlns.service.CongTacService;
////import com.ltjava.qlns.service.NhanVienService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.validation.annotation.Validated;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////import java.util.Optional;
////
////@Controller
////@RequestMapping("/congtac")
////public class CongTacController {
////
////    @Autowired
////    private CongTacService congTacService;
////
////    @Autowired
////    private NhanVienService nhanVienService;
////
////    @GetMapping
////    public String viewCongTac(Model model) {
////        List<CongTac> congTacs = congTacService.getAllCongTac();
////        model.addAttribute("congTacs", congTacs);
////        return "congtac/index";
////    }
////
////
////
////    @PostMapping
////    public String addCongTac( @ModelAttribute CongTac congTac) {
////        congTacService.createOrUpdateCongTac(congTac);
////        return "redirect:/congtac";
////    }
////
////
////
////    @PostMapping("/update")
////    public String updateCongTac( @ModelAttribute CongTac congTac) {
////        congTacService.createOrUpdateCongTac(congTac);
////        return "redirect:/congtac";
////    }
////
////    @GetMapping("/delete/{id}")
////    public String deleteCongTac(@PathVariable Long id) {
////        congTacService.deleteCongTac(id);
////        return "redirect:/congtac";
////    }
////}
//
//package com.ltjava.qlns.controller;
//
//import com.ltjava.qlns.model.ChuyenMon;
//import com.ltjava.qlns.model.CongTac;
//import com.ltjava.qlns.model.NhanVien;
//import com.ltjava.qlns.service.CongTacService;
//import com.ltjava.qlns.service.NhanVienService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/congtac")
//public class CongTacController {
//
//    @Autowired
//    private CongTacService congTacService;
//
//    @Autowired
//    private NhanVienService nhanVienService;
//
//    @GetMapping
//    public String showCongTacList(Model model) {
//        List<CongTac> congTacList = congTacService.getAllCongTac();
//        model.addAttribute("congTacList", congTacList);
//        return "congtac/index";
//    }
//
//    @PostMapping("/create")
//    public String addCongTac(@Valid CongTac congTac, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("congTacs", congTacService.getAllCongTac());
//            model.addAttribute("nhanViens",nhanVienService.getAllNhanVien());
//            return "congtac/index";
//        }
//        congTacService.saveCongTac(congTac);
//        return "redirect:/congtac";
//
//    }
//
//
//
//
//
//    @PostMapping("/update")
//    public String updateCongTac(@Valid CongTac congTac, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("congTacs", congTacService.getAllCongTac());
//            model.addAttribute("nhanViens", nhanVienService.getAllNhanVien());
//            return "congtac/index";
//        }
//        congTacService.saveCongTac(congTac);
//        return "redirect:/congtac";
//    }
//
//
//
//    @GetMapping("/delete/{id}")
//    public String deleteCongTac(@PathVariable Long id) {
//        congTacService.deleteCongTac(id);
//        return "redirect:/congtac";
//    }
//}


package com.ltjava.qlns.controller;
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

import com.ltjava.qlns.model.ChuyenMon;
import com.ltjava.qlns.model.CongTac;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.service.CongTacService;
import com.ltjava.qlns.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/congtac")
public class CongTacController {

    @Autowired
    private CongTacService congTacService;

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    public String viewCongTac(Model model) {
        List<CongTac> congTacs = congTacService.getAllCongTac();
        List<NhanVien> nhanViens = nhanVienService.getAllNhanViens();
        model.addAttribute("congTacs", congTacs);
        model.addAttribute("nhanViens", nhanViens);
        return "congtac/index";
    }

    @GetMapping("/export")
    public ResponseEntity<StreamingResponseBody> exportToExcel() {
        List<CongTac> congTacs = congTacService.getAllCongTac();

        StreamingResponseBody stream = out -> {
            try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                Sheet sheet = workbook.createSheet("CongTac");

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] headers = {"ID", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Địa Điểm", "Mục Đích", "ID Nhân Viên", "Ngày Tạo"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }

                // Create data rows
                int rowNum = 1;
                for (CongTac congTac : congTacs) {
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(congTac.getId());
                    row.createCell(1).setCellValue(congTac.getNgayBD().toString());
                    row.createCell(2).setCellValue(congTac.getNgayKT().toString());
                    row.createCell(3).setCellValue(congTac.getDiaDiemCT());
                    row.createCell(4).setCellValue(congTac.getMucDich());
                    row.createCell(5).setCellValue(congTac.getNhanVien().getId());
                    row.createCell(6).setCellValue(congTac.getNgayTao().toString());
                }

                workbook.write(bos);
                out.write(bos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=congtac.xlsx")
                .body(stream);
    }

    @PostMapping
    public String addCongTac(@Valid CongTac congTac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("chuyenMons", congTacService.getAllCongTac());
            model.addAttribute("nhanViens",nhanVienService.getAllNhanViens());
            return "congtac/index";
        }
        congTacService.saveCongTac(congTac);
        return "redirect:/congtac";
    }


    @PostMapping("/update")
    public String updateCongTac(@Valid CongTac congTac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("congTacs", congTacService.getAllCongTac());
            model.addAttribute("nhanViens",nhanVienService.getAllNhanViens());

            return "congtac/index";
        }
        congTacService.saveCongTac(congTac);
        return "redirect:/congtac";
    }

    @GetMapping("/delete/{id}")
    public String deleteCongTac(@PathVariable Long id) {
        congTacService.deleteCongTac(id);
        return "redirect:/congtac";
    }
}
