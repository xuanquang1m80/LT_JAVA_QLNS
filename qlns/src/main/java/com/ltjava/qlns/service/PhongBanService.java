package com.ltjava.qlns.service;

import com.ltjava.qlns.dto.PhongBanDTO;
import com.ltjava.qlns.exception.ResourceNotFoundException;
import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhongBanService {

    private final PhongBanRepository phongBanRepository;

    @Autowired
    public PhongBanService(PhongBanRepository phongBanRepository) {
        this.phongBanRepository = phongBanRepository;
    }

    public List<PhongBan> getAllPhongBans() {
        return phongBanRepository.findAll();
    }

    public PhongBan getPhongBanById(Long id) {
        return phongBanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PhongBan not found with id: " + id));
    }

    public PhongBan createPhongBan(PhongBan phongBan) {
        phongBan.setNgayTao(new Date()); // Thiết lập ngày tạo
        return phongBanRepository.save(phongBan);
    }

    public PhongBan updatePhongBan(Long id, PhongBan phongBanDetails) {
        PhongBan phongBan = getPhongBanById(id);
        phongBan.setTenPB(phongBanDetails.getTenPB());
        phongBan.setMoTa(phongBanDetails.getMoTa());
        phongBan.setNgaySua(new Date()); // Thiết lập ngày sửa
        return phongBanRepository.save(phongBan);
    }

    public void deletePhongBan(Long id) {
        PhongBan phongBan = getPhongBanById(id);
        phongBanRepository.delete(phongBan);
    }

    public List<PhongBanDTO> getAllPhongBan() {
        return phongBanRepository.findAll().stream()
                .map(phongBan -> new PhongBanDTO(
                        phongBan.getId(),
                        phongBan.getTenPB(),
                        phongBan.getMoTa(),
                        phongBan.getNhanViens().size()))
                .collect(Collectors.toList());
    }
}
