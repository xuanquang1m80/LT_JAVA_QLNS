package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.BangCap;
import com.ltjava.qlns.service.BangCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bangcap")
public class BangCapController {

    @Autowired
    private BangCapService bangCapService;

    @GetMapping
    public String viewBangCaps(Model model) {
        List<BangCap> bangCaps = bangCapService.getAllBangCaps();
        model.addAttribute("bangCaps", bangCaps);
        return "bangcap/index";
    }

    @PostMapping
    public String addBangCap(@ModelAttribute BangCap bangCap) {
        bangCapService.saveBangCap(bangCap);
        return "redirect:/bangcap";
    }

    @GetMapping("/delete/{id}")
    public String deleteBangCap(@PathVariable("id") Long id) {
        bangCapService.deleteBangCap(id);
        return "redirect:/bangcap";
    }

    @PostMapping("/update")
    public String updateBangCap(@ModelAttribute BangCap bangCap) {
        bangCapService.saveBangCap(bangCap);
        return "redirect:/bangcap";
    }
}
