package com.cross.jp.projectmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
    @GetMapping("/add")
    public String addClient(Model model){
        return "client_add";
    }
}
