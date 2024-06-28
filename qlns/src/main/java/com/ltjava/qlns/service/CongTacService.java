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
    private CongTacRepository congtacRepository;

    public List<CongTac> getAllCongTac() {
        return congtacRepository.findAll();
    }

    public Optional<CongTac> getCongTacById(Long id) {
        return congtacRepository.findById(id);
    }

    public CongTac saveCongTac(CongTac congTac) {
        return congtacRepository.save(congTac);
    }

    public void deleteCongTac(Long id) {
        congtacRepository.deleteById(id);
    }
}
