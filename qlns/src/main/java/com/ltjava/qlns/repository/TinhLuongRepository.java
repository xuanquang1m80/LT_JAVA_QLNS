package com.ltjava.qlns.repository;

import com.ltjava.qlns.model.TinhLuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinhLuongRepository extends JpaRepository<TinhLuong, Long> {
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh nếu cần
}
