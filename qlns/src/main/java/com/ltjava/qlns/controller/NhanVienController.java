package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private LoaiNhanVienService loaiNhanVienService;

    @Autowired
    private PhongBanService phongBanService;

    @Autowired
    private BangCapService bangCapService;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private ChuyenMonService chuyenMonService;

    @Autowired
    private TrangThaiService trangThaiService;

    @Autowired
    private TrinhDoService trinhDoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("loaiNhanViens", loaiNhanVienService.getAllLoaiNhanVien());
        model.addAttribute("phongBans", phongBanService.getAllPhongBan());
        model.addAttribute("bangCaps", bangCapService.getAllBangCaps());
        model.addAttribute("chucVus", chucVuService.getAllChucVu());
        model.addAttribute("chuyenMons", chuyenMonService.getAllChuyenMon());
        model.addAttribute("trangThais", trangThaiService.getAllTrangThai());
        model.addAttribute("trinhDos", trinhDoService.getAllTrinhDo());
        return "nhanvien/addNhanVien";
    }

    @PostMapping("/add")
    public String addNhanVien(@ModelAttribute NhanVien nhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loaiNhanViens", loaiNhanVienService.getAllLoaiNhanVien());
            model.addAttribute("phongBans", phongBanService.getAllPhongBan());
            model.addAttribute("bangCaps", bangCapService.getAllBangCaps());
            model.addAttribute("chucVus", chucVuService.getAllChucVu());
            model.addAttribute("chuyenMons", chuyenMonService.getAllChuyenMon());
            model.addAttribute("trangThais", trangThaiService.getAllTrangThai());
            model.addAttribute("trinhDos", trinhDoService.getAllTrinhDo());
            return "nhanvien/addNhanVien";
        }
        nhanVienService.saveNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }
    @GetMapping
    public String listNhanViens(Model model) {
        List<NhanVien> nhanViens = nhanVienService.getAllNhanViens();
        model.addAttribute("nhanViens", nhanViens);
        return "nhanvien/index";
    }

}
