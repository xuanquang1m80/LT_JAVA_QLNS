package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private LoaiNhanVienService loaiNhanVienService;

    @Autowired
    private PhongBanService phongBanService;

    @Autowired
    private BangCapService bangCapService;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private ChuyenMonService chuyenMonService;

    @Autowired
    private TrangThaiService trangThaiService;

    @Autowired
    private TrinhDoService trinhDoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public String listNhanVien(Model model) {
        List<NhanVien> nhanViens = nhanVienService.findAll();
        model.addAttribute("nhanViens", nhanViens);
        return "nhanvien";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("loaiNhanViens", loaiNhanVienService.getAllLoaiNhanVien());
        model.addAttribute("phongBans", phongBanService.getAllPhongBans());
        model.addAttribute("bangCaps", bangCapService.getAllBangCaps());
        model.addAttribute("chucVus", chucVuService.getAllChucVu());
        model.addAttribute("chuyenMons", chuyenMonService.getAllChuyenMons());
        model.addAttribute("trangThais", trangThaiService.getAllTrangThai());
        model.addAttribute("trinhDos", trinhDoService.getAllTrinhDos());
        return "add-nhanvien";
    }

    @PostMapping("/add")
    public String addNhanVien(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loaiNhanViens", loaiNhanVienService.getAllLoaiNhanVien());
            model.addAttribute("phongBans", phongBanService.getAllPhongBans());
            model.addAttribute("bangCaps", bangCapService.getAllBangCaps());
            model.addAttribute("chucVus", chucVuService.getAllChucVu());
            model.addAttribute("chuyenMons", chuyenMonService.getAllChuyenMons());
            model.addAttribute("trangThais", trangThaiService.getAllTrangThai());
            model.addAttribute("trinhDos", trinhDoService.getAllTrinhDos());
            return "add-nhanvien";
        }

        // Xử lý và lưu ảnh vào thư mục và cập nhật đường dẫn vào nhanVien
        try {
            String imagePath = saveImage(nhanVien.getImageFile()); // Hàm lưu ảnh vào thư mục và trả về đường dẫn
            nhanVien.setImage(imagePath); // Cập nhật đường dẫn ảnh vào đối tượng nhanVien
        } catch (IOException e) {
            // Xử lý ngoại lệ khi lưu ảnh không thành công
            model.addAttribute("errorMessage", "Failed to save image.");
            return "add-nhanvien";
        }

        nhanVienService.save(nhanVien);
        return "redirect:/nhanvien";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NhanVien nhanVien = nhanVienService.findById(id);
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("loaiNhanViens", loaiNhanVienService.getAllLoaiNhanVien());
        model.addAttribute("phongBans", phongBanService.getAllPhongBans());
        model.addAttribute("bangCaps", bangCapService.getAllBangCaps());
        model.addAttribute("chucVus", chucVuService.getAllChucVu());
        model.addAttribute("chuyenMons", chuyenMonService.getAllChuyenMons());
        model.addAttribute("trangThais", trangThaiService.getAllTrangThai());
        model.addAttribute("trinhDos", trinhDoService.getAllTrinhDos());
        return "edit-nhanvien";
    }


    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable Long id, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult result, Model model, RedirectAttributes redirectAttributes) {


        // Xử lý và lưu ảnh vào thư mục và cập nhật đường dẫn vào nhanVien
        try {
            String imagePath = saveImage(nhanVien.getImageFile());
            nhanVien.setImage(imagePath);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Failed to save image.");
            return "redirect:/403";
        }

        nhanVienService.update(id, nhanVien);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật nhân viên thành công.");
        return "redirect:/nhanvien";
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        String uploadDir = "qlns/src/main/resources/static/assets/img/"; // Đường dẫn thư mục lưu trữ ảnh
        String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            System.out.println("Thư mục chưa tồn tại. Tạo thư mục mới...");
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = imageFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName; // Trả về tên file gốc
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            nhanVienService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa nhân viên thành công.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/nhanvien";
    }

    @GetMapping("/detail/{id}")
    public String showNhanVienDetail(@PathVariable Long id, Model model) {
        NhanVien nhanVien = nhanVienService.findById(id);
        model.addAttribute("nhanVien", nhanVien);
        return "detail-nhanvien";
    }

}
