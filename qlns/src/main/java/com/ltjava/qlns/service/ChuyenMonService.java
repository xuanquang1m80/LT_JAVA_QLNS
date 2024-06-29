package com.ltjava.qlns.service;

import com.ltjava.qlns.model.ChuyenMon;
import com.ltjava.qlns.model.TrinhDo;
import com.ltjava.qlns.repository.ChuyenMonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuyenMonService {

    @Autowired
    private ChuyenMonRepository chuyenMonRepository;

    public List<ChuyenMon> findAll() {
        return chuyenMonRepository.findAll();
    }

    public List<ChuyenMon> getAllChuyenMons() {
        return chuyenMonRepository.findAll();
    }

    public void save(ChuyenMon chuyenMon) {
        chuyenMonRepository.save(chuyenMon);
    }

    public void deleteById(Long id) {
        chuyenMonRepository.deleteById(id);
    }
}
