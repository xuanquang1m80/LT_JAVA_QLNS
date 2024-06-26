package com.ltjava.qlns.service;

import com.ltjava.qlns.model.CongTac;
import com.ltjava.qlns.repository.CongTacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongTacService {

    @Autowired
    private CongTacRepository congTacRepository;

    public List<CongTac> getAllCongTac() {
        return congTacRepository.findAll();
    }

    public Optional<CongTac> getCongTacById(Long id) {
        return congTacRepository.findById(id);
    }

    public CongTac createOrUpdateCongTac(CongTac congTac) {
        return congTacRepository.save(congTac);
    }

    public void deleteCongTac(Long id) {
        congTacRepository.deleteById(id);
    }
}
