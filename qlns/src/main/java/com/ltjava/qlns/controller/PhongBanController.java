package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// @CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/phongbans")
public class PhongBanController {

    private final PhongBanService phongBanService;

    @Autowired
    public PhongBanController(PhongBanService phongBanService) {
        this.phongBanService = phongBanService;
    }

    @GetMapping
    public List<PhongBan> getAllPhongBans() {
        return phongBanService.getAllPhongBans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhongBan> getPhongBanById(@PathVariable Long id) {
        PhongBan phongBan = phongBanService.getPhongBanById(id);
        return ResponseEntity.ok(phongBan);
    }

    @PostMapping
    public ResponseEntity<PhongBan> createPhongBan(@RequestBody PhongBan phongBan) {
        PhongBan createdPhongBan = phongBanService.createPhongBan(phongBan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPhongBan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhongBan> updatePhongBan(@PathVariable Long id, @RequestBody PhongBan phongBan) {
        PhongBan updatedPhongBan = phongBanService.updatePhongBan(id, phongBan);
        return ResponseEntity.ok(updatedPhongBan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhongBan(@PathVariable Long id) {
        phongBanService.deletePhongBan(id);
        return ResponseEntity.noContent().build();
    }
}

