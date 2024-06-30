package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.TinhLuong;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.repository.TinhLuongRepository;
import com.ltjava.qlns.service.TinhLuongService;
import com.ltjava.qlns.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tinhluong")
public class TinhLuongController {

    @Autowired
    private TinhLuongService tinhLuongService;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private TinhLuongRepository tinhLuongRepository;

    @GetMapping
    public String showTinhLuongForm(Model model) {
        List<NhanVien> listOfEmployees = nhanVienRepository.findAll();
        model.addAttribute("listOfEmployees", listOfEmployees);
        model.addAttribute("tinhLuong", new TinhLuong());
        List<TinhLuong> processedEmployees = tinhLuongRepository.findAll();
        model.addAttribute("processedEmployees", processedEmployees);
        return "tinhluong/index";
    }

    @PostMapping("/tinh")
    public String tinhLuong(@ModelAttribute TinhLuong tinhLuong, @RequestParam("cccd") String cccd, Model model) {
        try {
            // Set NhanVien object using CCCD
            NhanVien nhanVien = nhanVienRepository.findByCCCD(cccd).orElse(null);
            if (nhanVien == null) {
                throw new RuntimeException("Không tìm thấy nhân viên với CCCD: " + cccd);
            }
            tinhLuong.setNhanVien(nhanVien);
            tinhLuong.setNgayTinhLuong(new Date());
            TinhLuong result = tinhLuongService.tinhLuong(tinhLuong);
            model.addAttribute("result", result);
            return "redirect:/tinhluong";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "tinhluong/index";
        }
    }
}
