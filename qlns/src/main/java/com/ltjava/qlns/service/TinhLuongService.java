package com.ltjava.qlns.service;

import com.ltjava.qlns.model.TinhLuong;
import com.ltjava.qlns.repository.TinhLuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TinhLuongService {

    @Autowired
    private TinhLuongRepository tinhLuongRepository;

    public List<TinhLuong> getAllTinhLuongs() {
        return tinhLuongRepository.findAll();
    }

    public Optional<TinhLuong> getTinhLuongById(Long id) {
        return tinhLuongRepository.findById(id);
    }

    public TinhLuong saveTinhLuong(TinhLuong tinhLuong) {
        return tinhLuongRepository.save(tinhLuong);
    }

    public void deleteTinhLuong(Long id) {
        tinhLuongRepository.deleteById(id);
    }
}
