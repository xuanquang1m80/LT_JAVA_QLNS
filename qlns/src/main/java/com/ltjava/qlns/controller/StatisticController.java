package com.ltjava.qlns.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/thongke")
public class StatisticController {

    @GetMapping()
    public String test(){
        return "statistic/index";
    }

}
