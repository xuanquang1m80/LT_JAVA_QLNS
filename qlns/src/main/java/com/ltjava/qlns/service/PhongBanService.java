package com.ltjava.qlns.service;

import com.ltjava.qlns.exception.ResourceNotFoundException;
import com.ltjava.qlns.model.PhongBan;
import com.ltjava.qlns.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PhongBanService {

    @Autowired
    private PhongBanRepository phongBanRepository;

    public List<PhongBan> getAllPhongBans() {
        return phongBanRepository.findAll();
    }

    public PhongBan getPhongBanById(Long id) {
        return phongBanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PhongBan not found with id: " + id));
    }

    public PhongBan createPhongBan(PhongBan phongBan) {
        phongBan.setNgayTao(new Date());
        return phongBanRepository.save(phongBan);
    }

    public PhongBan updatePhongBan(Long id, PhongBan phongBanDetails) {
        PhongBan phongBan = getPhongBanById(id);
        phongBan.setTenPB(phongBanDetails.getTenPB());
        phongBan.setMoTa(phongBanDetails.getMoTa());
        phongBan.setNgaySua(new Date());
        return phongBanRepository.save(phongBan);
    }

    public void deletePhongBan(Long id) {
        PhongBan phongBan = getPhongBanById(id);
        phongBanRepository.delete(phongBan);
    }
}
