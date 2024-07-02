package com.ltjava.qlns.service;

import com.ltjava.qlns.model.TinhLuong;

public interface TinhLuongService {
    TinhLuong tinhLuong(TinhLuong tinhLuong);
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
