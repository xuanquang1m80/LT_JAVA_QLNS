package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.ChucVu;
import com.ltjava.qlns.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chucvu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping
    public String viewChucVu(Model model) {
        List<ChucVu> chucVus = chucVuService.getAllChucVu();
        model.addAttribute("chucVus", chucVus);
        return "chucvu/index";
    }

    @PostMapping
    public String addChucVu(@ModelAttribute ChucVu chucVu) {
        chucVuService.saveChucVu(chucVu);
        return "redirect:/chucvu";
    }

    @GetMapping("/delete/{id}")
    public String deleteChucVu(@PathVariable("id") Long id) {
        chucVuService.deleteChucVu(id);
        return "redirect:/chucvu";
    }

    @PostMapping("/update")
    public String updateChucVu(@ModelAttribute ChucVu chucVu) {
        chucVuService.saveChucVu(chucVu);
        return "redirect:/chucvu";
    }
}
