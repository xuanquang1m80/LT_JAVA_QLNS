////package com.ltjava.qlns.controller;
////
////import com.ltjava.qlns.model.CongTac;
////import com.ltjava.qlns.model.NhanVien;
////import com.ltjava.qlns.service.CongTacService;
////import com.ltjava.qlns.service.NhanVienService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.validation.annotation.Validated;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////import java.util.Optional;
////
////@Controller
////@RequestMapping("/congtac")
////public class CongTacController {
////
////    @Autowired
////    private CongTacService congTacService;
////
////    @Autowired
////    private NhanVienService nhanVienService;
////
////    @GetMapping
////    public String viewCongTac(Model model) {
////        List<CongTac> congTacs = congTacService.getAllCongTac();
////        model.addAttribute("congTacs", congTacs);
////        return "congtac/index";
////    }
////
////
////
////    @PostMapping
////    public String addCongTac( @ModelAttribute CongTac congTac) {
////        congTacService.createOrUpdateCongTac(congTac);
////        return "redirect:/congtac";
////    }
////
////
////
////    @PostMapping("/update")
////    public String updateCongTac( @ModelAttribute CongTac congTac) {
////        congTacService.createOrUpdateCongTac(congTac);
////        return "redirect:/congtac";
////    }
////
////    @GetMapping("/delete/{id}")
////    public String deleteCongTac(@PathVariable Long id) {
////        congTacService.deleteCongTac(id);
////        return "redirect:/congtac";
////    }
////}
//
//package com.ltjava.qlns.controller;
//
//import com.ltjava.qlns.model.ChuyenMon;
//import com.ltjava.qlns.model.CongTac;
//import com.ltjava.qlns.model.NhanVien;
//import com.ltjava.qlns.service.CongTacService;
//import com.ltjava.qlns.service.NhanVienService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/congtac")
//public class CongTacController {
//
//    @Autowired
//    private CongTacService congTacService;
//
//    @Autowired
//    private NhanVienService nhanVienService;
//
//    @GetMapping
//    public String showCongTacList(Model model) {
//        List<CongTac> congTacList = congTacService.getAllCongTac();
//        model.addAttribute("congTacList", congTacList);
//        return "congtac/index";
//    }
//
//    @PostMapping("/create")
//    public String addCongTac(@Valid CongTac congTac, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("congTacs", congTacService.getAllCongTac());
//            model.addAttribute("nhanViens",nhanVienService.getAllNhanVien());
//            return "congtac/index";
//        }
//        congTacService.saveCongTac(congTac);
//        return "redirect:/congtac";
//
//    }
//
//
//
//
//
//    @PostMapping("/update")
//    public String updateCongTac(@Valid CongTac congTac, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("congTacs", congTacService.getAllCongTac());
//            model.addAttribute("nhanViens", nhanVienService.getAllNhanVien());
//            return "congtac/index";
//        }
//        congTacService.saveCongTac(congTac);
//        return "redirect:/congtac";
//    }
//
//
//
//    @GetMapping("/delete/{id}")
//    public String deleteCongTac(@PathVariable Long id) {
//        congTacService.deleteCongTac(id);
//        return "redirect:/congtac";
//    }
//}


package com.ltjava.qlns.controller;

import com.ltjava.qlns.model.ChuyenMon;
import com.ltjava.qlns.model.CongTac;
import com.ltjava.qlns.model.NhanVien;
import com.ltjava.qlns.service.CongTacService;
import com.ltjava.qlns.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/congtac")
public class CongTacController {

    @Autowired
    private CongTacService congTacService;

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    public String viewCongTac(Model model) {
        List<CongTac> congTacs = congTacService.getAllCongTac();
        List<NhanVien> nhanViens = nhanVienService.getdAllNhanVien();
        model.addAttribute("congTacs", congTacs);
        model.addAttribute("nhanViens", nhanViens);
        return "congtac/index";
    }

//    @GetMapping("congtac/create")
//    public String showCreateForm(Model model) {
//        CongTac congTac = new CongTac();
//        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
//        model.addAttribute("congTac", congTac);
//        model.addAttribute("nhanVienList", nhanVienList);
//        return "congtac/create";
//    }

//    @PostMapping("/save")
//    public String saveCongTac(@Validated @ModelAttribute CongTac congTac) {
//        congTacService.saveCongTac(congTac);
//        return "redirect:/congtac";
//    }

//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Optional<CongTac> congTac = congTacService.getCongTacById(id);
//        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
//        if (congTac.isPresent()) {
//            model.addAttribute("congTac", congTac.get());
//            model.addAttribute("nhanVienList", nhanVienList);
//            return "congtac/edit";
//        } else {
//            return "redirect:/congtac";
//        }
//    }

//    @PostMapping("/update")
//    public String updateCongTac(@Validated @ModelAttribute CongTac congTac) {
//        congTacService.saveCongTac(congTac);
//        return "redirect:/congtac";
//    }

    @PostMapping
    public String addCongTac(@Valid CongTac congTac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("chuyenMons", congTacService.getAllCongTac());
            model.addAttribute("nhanViens",nhanVienService.getdAllNhanVien());
            return "congtac/index";
        }
        congTacService.saveCongTac(congTac);
        return "redirect:/congtac";
    }


    @PostMapping("/update")
    public String updateCongTac(@Valid CongTac congTac, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("congTacs", congTacService.getAllCongTac());
            model.addAttribute("nhanViens",nhanVienService.getdAllNhanVien());

            return "congtac/index";
        }
        congTacService.saveCongTac(congTac);
        return "redirect:/congtac";
    }

    @GetMapping("/delete/{id}")
    public String deleteCongTac(@PathVariable Long id) {
        congTacService.deleteCongTac(id);
        return "redirect:/congtac";
    }
}
