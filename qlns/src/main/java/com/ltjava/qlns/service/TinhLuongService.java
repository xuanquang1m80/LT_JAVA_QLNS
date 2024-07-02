package com.ltjava.qlns.service;

import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.model.TinhLuong;
import com.ltjava.qlns.repository.TinhLuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class TinhLuongService {

    @Autowired
    private TinhLuongRepository tinhLuongRepository;

    public TinhLuong tinhLuong(NhanVien nhanVien, TinhLuong tinhLuong) {
        // Lấy các thông tin cần thiết
        BigDecimal luongThang = tinhLuong.getLuongThang();
        int ngayCong = tinhLuong.getNgayCong();
        BigDecimal phuCap = tinhLuong.getPhuCap();
        BigDecimal tamUng = tinhLuong.getTamUng();

        // Số ngày làm việc chuẩn (có thể cấu hình)
        int soNgayLamViecChuan = 26;

        // Tính lương thực nhận
        BigDecimal thucLanh = luongThang.divide(BigDecimal.valueOf(soNgayLamViecChuan), RoundingMode.CEILING.HALF_UP)
                .multiply(BigDecimal.valueOf(ngayCong))
                .add(phuCap)
                .subtract(tamUng);

        // Gán lương thực nhận vào tinhLuong
        tinhLuong.setThucLanh(thucLanh);
        tinhLuong.setNgayTinhLuong(new Date());

        return tinhLuongRepository.save(tinhLuong);
    }
}