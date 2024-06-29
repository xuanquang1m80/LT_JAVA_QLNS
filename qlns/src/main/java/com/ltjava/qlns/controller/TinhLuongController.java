package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.model.TinhLuong;
import com.ltjava.qlns.service.ChucVuService;
import com.ltjava.qlns.service.NhanVienService;
import com.ltjava.qlns.service.TinhLuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tinhluong")
public class TinhLuongController {

    @Autowired
    private TinhLuongService tinhLuongService;

    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChucVuService chucVuService;
    @GetMapping
    public String viewTinhLuongPage(Model model) {
        List<NhanVien> listOfEmployees = nhanVienService.getAllNhanViens(); // Sửa đổi này để lấy danh sách nhân viên
        model.addAttribute("tinhLuong", new TinhLuong());
        model.addAttribute("listOfEmployees", listOfEmployees); // Thêm danh sách nhân viên vào model
        return "tinhluong/index";
    }

    @PostMapping("/tinh")
    public String tinhLuong(@ModelAttribute TinhLuong tinhLuong, @RequestParam Long nhanVienId, Model model) {
        Optional<NhanVien> nhanVienOpt = nhanVienService.getNhanVienById(nhanVienId);
        if (nhanVienOpt.isPresent()) {
            NhanVien nhanVien = nhanVienOpt.get();
            TinhLuong result = tinhLuongService.tinhLuong(nhanVien, tinhLuong);
            model.addAttribute("result", result);
            return "tinhluong/result";
        } else {
            model.addAttribute("error", "NhanVien not found");
            List<NhanVien> listOfEmployees = nhanVienService.getAllNhanViens(); // Sửa đổi này để lấy lại danh sách nhân viên
            model.addAttribute("listOfEmployees", listOfEmployees); // Thêm danh sách nhân viên vào model
            return "tinhluong/index";
        }
    }
}
