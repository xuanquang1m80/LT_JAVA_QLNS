package com.ltjava.qlns.service;

import com.ltjava.qlns.model.BangCap;
import com.ltjava.qlns.repository.BangCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BangCapService {

    @Autowired
    private BangCapRepository bangCapRepository;

    public List<BangCap> getAllBangCaps() {
        return bangCapRepository.findAll();
    }

    public Optional<BangCap> getBangCapById(Long id) {
        return bangCapRepository.findById(id);
    }

    public BangCap saveBangCap(BangCap bangCap) {
        return bangCapRepository.save(bangCap);
    }

    public void deleteBangCap(Long id) {
        bangCapRepository.deleteById(id);
    }
}
