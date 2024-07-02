package com.ltjava.qlns.controller;


import com.ltjava.qlns.model.TinhLuong;
import com.ltjava.qlns.service.AccountService;
import com.ltjava.qlns.repository.NhanVienRepository;
import com.ltjava.qlns.service.TinhLuongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/detailluong")
public class DetailLuongController {

    @Autowired
    private TinhLuongServiceImpl tinhLuongService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @GetMapping("/")
    public String getTinhLuongByCurrentUser(Model model) {
        List<TinhLuong> processedEmployees = tinhLuongService.getTinhLuongByCurrentUser();
        model.addAttribute("processedEmployees", processedEmployees);
        return "tinhluong/index";
    }

}
