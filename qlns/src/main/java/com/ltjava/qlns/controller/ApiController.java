package com.ltjava.qlns.controller;


import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    //test
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/phongban")
    public List<PhongBan> getAllPhongBan() {
        return nhanVienService.getAllPhongBan();
    }
}
