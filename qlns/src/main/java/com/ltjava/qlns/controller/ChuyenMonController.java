package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.ChuyenMon;
import com.ltjava.qlns.service.ChuyenMonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/chuyenmon")
public class ChuyenMonController {

    @Autowired
    private ChuyenMonService chuyenMonService;

    @GetMapping
    public String viewChuyenMon(Model model) {
        model.addAttribute("chuyenMons", chuyenMonService.findAll());
        return "chuyenmon/index";
    }

    @PostMapping
    public String addChuyenMon(@Valid ChuyenMon chuyenMon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("chuyenMons", chuyenMonService.findAll());
            return "chuyenmon/index";
        }
        chuyenMonService.save(chuyenMon);
        return "redirect:/chuyenmon";
    }

    @PostMapping("/update")
    public String updateChuyenMon(@Valid ChuyenMon chuyenMon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("chuyenMons", chuyenMonService.findAll());
            return "chuyenmon/index";
        }
        chuyenMonService.save(chuyenMon);
        return "redirect:/chuyenmon";
    }

    @GetMapping("/delete/{id}")
    public String deleteChuyenMon(@PathVariable("id") Long id) {
        chuyenMonService.deleteById(id);
        return "redirect:/chuyenmon";
    }
}
