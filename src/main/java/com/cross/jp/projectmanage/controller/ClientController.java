package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.dto.ClientDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    ClientService service;
    @GetMapping("/list")
    public String clientList(Model model){
        List<Client> clientList = service.getClientList();
        model.addAttribute("clientList",clientList);
        return "client_list";
    }
    @GetMapping("/{id}")
    public String clientDetail(Model model, @PathVariable("id")Integer id){
        return "";
    }
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
