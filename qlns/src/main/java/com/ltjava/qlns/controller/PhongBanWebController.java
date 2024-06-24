package com.ltjava.qlns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhongBanWebController {

    @GetMapping("/phongbans")
    public String showPhongBanList() {
        return "PhongBan/list-PhongBan"; // Trả về tên template Thymeleaf
    }
}
