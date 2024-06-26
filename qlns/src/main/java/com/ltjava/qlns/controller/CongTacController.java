package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.CongTac;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.service.CongTacService;
import com.ltjava.qlns.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CongTacController {

    @Autowired
    private CongTacService congTacService;

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<CongTac> congTacList = congTacService.getAllCongTac();
        model.addAttribute("congTacList", congTacList);
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        CongTac congTac = new CongTac();
        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
        model.addAttribute("congTac", congTac);
        model.addAttribute("nhanVienList", nhanVienList);
        return "create";
    }

    @PostMapping("/save")
    public String saveCongTac(@Validated @ModelAttribute CongTac congTac) {
        congTacService.createOrUpdateCongTac(congTac);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<CongTac> congTac = congTacService.getCongTacById(id);
        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
        if (congTac.isPresent()) {
            model.addAttribute("congTac", congTac.get());
            model.addAttribute("nhanVienList", nhanVienList);
            return "edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/update")
    public String updateCongTac(@Validated @ModelAttribute CongTac congTac) {
        congTacService.createOrUpdateCongTac(congTac);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCongTac(@PathVariable Long id) {
        congTacService.deleteCongTac(id);
        return "redirect:/";
    }
}
