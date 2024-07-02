package com.ltjava.qlns.controller;


import com.ltjava.qlns.dto.ChucVuDTO;
import com.ltjava.qlns.dto.PhongBanDTO;
import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.service.ChucVuService;
import com.ltjava.qlns.service.NhanVienService;
import com.ltjava.qlns.service.PhongBanService;
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
    private PhongBanService phongBanService;

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/phongban")
    public List<PhongBanDTO> getAllPhongBan() {
        return phongBanService.getAllPhongBan();
    }

    @GetMapping("/chucvu")
    public List<ChucVuDTO> getAllChucVu(){
        return chucVuService.findAllChucVu();
    }
}
