package com.ltjava.qlns.controller;


import com.ltjava.qlns.dto.ChucVuDTO;
import com.ltjava.qlns.dto.PhongBanDTO;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CongTacService congTacService;


    @GetMapping("/phongban")
    public List<PhongBanDTO> getAllPhongBan() {
        return phongBanService.getAllPhongBan();
    }

    @GetMapping("/chucvu")
    public List<ChucVuDTO> getAllChucVu(){
        return chucVuService.findAllChucVu();
    }


    @GetMapping("/totalemployee")
    public ResponseEntity<Long> getEmployeeCount() {
        long totalEmployees = nhanVienService.countEmployees();
        return ResponseEntity.ok(totalEmployees);
    }

    @GetMapping("/totalaccount")
    public ResponseEntity<Long> getAccoutnsTotal() {
        long totalAccount = accountService.countAccounts();
        return ResponseEntity.ok(totalAccount);
    }

    @GetMapping("/totalcongtac")
    public ResponseEntity<Long> getAllCongtac() {
        long totalAccount = congTacService.countLichCT();
        return ResponseEntity.ok(totalAccount);
    }

    @GetMapping("/nhanvien/download")
    public ResponseEntity<InputStreamResource> exportNhanVienToExcel() throws IOException {
        List<NhanVien> nhanVienList = nhanVienService.getAllNhanViens();
        ByteArrayInputStream in = nhanVienService.exportNhanVienListToExcel(nhanVienList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=nhan_vien.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }

}
