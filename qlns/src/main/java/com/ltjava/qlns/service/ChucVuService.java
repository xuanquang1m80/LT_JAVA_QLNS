package com.ltjava.qlns.service;

import com.ltjava.qlns.dto.ChucVuDTO;
import com.ltjava.qlns.dto.PhongBanDTO;
import com.ltjava.qlns.model.ChucVu;
import com.ltjava.qlns.repository.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;

    public List<ChucVu> getAllChucVu() {
        return chucVuRepository.findAll();
    }

    public Optional<ChucVu> getChucVuById(Long id) {
        return chucVuRepository.findById(id);
    }

    public ChucVu saveChucVu(ChucVu chucVu) {
        return chucVuRepository.save(chucVu);
    }

    public void deleteChucVu(Long id) {
        chucVuRepository.deleteById(id);
    }

    public List<ChucVuDTO> findAllChucVu() {
        return chucVuRepository.findAll().stream()
                .map(chucVu -> new ChucVuDTO(
                        chucVu.getId(),
                        chucVu.getTenChucVu(),
                        chucVu.getMoTa(),
                        chucVu.getNhanViens().size()))
                .collect(Collectors.toList());
    }
}
