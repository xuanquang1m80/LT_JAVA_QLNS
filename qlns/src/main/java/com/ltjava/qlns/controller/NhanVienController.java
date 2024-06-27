package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    public String listNhanVien(Model model) {
        List<NhanVien> nhanViens = nhanVienService.findAll();
        model.addAttribute("nhanViens", nhanViens);
        return "nhanvien";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "add-nhanvien";
    }

    @PostMapping("/add")
    public String addNhanVien(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-nhanvien";
        }
        nhanVienService.save(nhanVien);
        return "redirect:/nhanvien";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NhanVien nhanVien = nhanVienService.findById(id);
        model.addAttribute("nhanVien", nhanVien);
        return "edit-nhanvien";
    }

    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable Long id, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-nhanvien";
        }
        nhanVienService.update(id, nhanVien);
        return "redirect:/nhanvien";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable Long id) {
        nhanVienService.delete(id);
        return "redirect:/nhanvien";
    }
}
