package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/phongban")
public class PhongBanController {

    @Autowired
    private PhongBanService phongBanService;

    @GetMapping
    public String viewPhongBans(Model model) {
        List<PhongBan> phongBans = phongBanService.getAllPhongBans();
        model.addAttribute("phongBans", phongBans);
        return "phongban/index";
    }

    @PostMapping
    public String addPhongBan(@ModelAttribute PhongBan phongBan) {
        phongBanService.createPhongBan(phongBan);
        return "redirect:/phongban";
    }

    @GetMapping("/delete/{id}")
    public String deletePhongBan(@PathVariable("id") Long id) {
        phongBanService.deletePhongBan(id);
        return "redirect:/phongban";
    }

    @PostMapping("/update")
    public String updatePhongBan(@ModelAttribute PhongBan phongBan) {
        phongBanService.updatePhongBan(phongBan.getId(), phongBan);
        return "redirect:/phongban";
    }
}
