package com.ltjava.qlns.service;

import com.ltjava.qlns.model.TinhLuong;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.repository.TinhLuongRepository;
import com.ltjava.qlns.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TinhLuongServiceImpl implements TinhLuongService {

    @Autowired
    private TinhLuongRepository tinhLuongRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public TinhLuong tinhLuong(TinhLuong tinhLuong) {
        // Tìm nhân viên từ ID
        Optional<NhanVien> nhanVienOpt = nhanVienRepository.findById(tinhLuong.getNhanVien().getId());
        if (nhanVienOpt.isPresent()) {
            NhanVien nhanVien = nhanVienOpt.get();

            BigDecimal luongThang = tinhLuong.getLuongThang();
            Integer ngayCong = tinhLuong.getNgayCong();
            Integer ngayNghi = tinhLuong.getNgayNghi();
            BigDecimal phuCap = tinhLuong.getPhuCap();
            BigDecimal tamUng = tinhLuong.getTamUng();
            Double heSoLuong = nhanVien.getChucVu().getHeSoLuong();

            // Tổng số ngày làm việc trong tháng (có thể là 22 ngày chẳng hạn)
            int tongNgayLamViecTrongThang = 26;

            // Tính lương thực lãnh
            BigDecimal luongNgay = luongThang.divide(BigDecimal.valueOf(tongNgayLamViecTrongThang), BigDecimal.ROUND_HALF_UP);
            BigDecimal thucLanh = luongNgay.multiply(BigDecimal.valueOf(ngayCong)).multiply(BigDecimal.valueOf(heSoLuong))
                    .add(phuCap)
                    .subtract(tamUng);

            tinhLuong.setThucLanh(thucLanh);

            // Lưu thông tin tính lương vào cơ sở dữ liệu
            return tinhLuongRepository.save(tinhLuong);
        } else {
            throw new RuntimeException("Không tìm thấy nhân viên với ID: " + tinhLuong.getNhanVien().getId());
        }
    }
}
