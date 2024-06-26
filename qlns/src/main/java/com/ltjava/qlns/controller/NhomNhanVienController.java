package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.NhomNhanVien;
import com.ltjava.qlns.service.NhomNhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhomnhanvien")
public class NhomNhanVienController {

    @Autowired
    private NhomNhanVienService nhomNhanVienService;

    @GetMapping
    public String viewNhomNhanViens(Model model) {
        List<NhomNhanVien> nhomNhanViens = nhomNhanVienService.getAllNhomNhanViens();
        model.addAttribute("nhomNhanViens", nhomNhanViens);
        return "nhomnhanvien/index";
    }

    @PostMapping
    public String addNhomNhanVien(@ModelAttribute NhomNhanVien nhomNhanVien) {
        nhomNhanVienService.createNhomNhanVien(nhomNhanVien);
        return "redirect:/nhomnhanvien";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhomNhanVien(@PathVariable("id") Long id) {
        nhomNhanVienService.deleteNhomNhanVien(id);
        return "redirect:/nhomnhanvien";
    }

    @PostMapping("/update")
    public String updateNhomNhanVien(@ModelAttribute NhomNhanVien nhomNhanVien) {
        nhomNhanVienService.updateNhomNhanVien(nhomNhanVien.getId(), nhomNhanVien);
        return "redirect:/nhomnhanvien";
    }
}

