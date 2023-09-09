package com.cross.jp.projectmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/proceeds")
public class ProceedsController {
    @GetMapping("/list")
    public String proceedsList(Model model){
        return "proceeds";
    }
}
