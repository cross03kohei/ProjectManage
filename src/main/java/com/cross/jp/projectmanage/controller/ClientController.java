package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.dto.ClientDto;
import com.cross.jp.projectmanage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    ClientService service;
    @GetMapping("/add")
    public String addClient(Model model){
        model.addAttribute("clientDto", new ClientDto());
        return "client_add";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute ClientDto clientDto, Model model){
        service.save(clientDto);
        return "redirect:/project/list";
    }
}
