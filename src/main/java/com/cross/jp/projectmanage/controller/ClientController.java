package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.dto.ClientDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    @GetMapping("/search")
    public String searchClient(Model model,@RequestParam("name")String name){
        List<Client> clientList = service.searchClient(name);
        model.addAttribute("clientList",clientList);
        return "client_list";
    }
    @GetMapping("/{id}")
    public String clientDetail(Model model, @PathVariable("id")Integer id){
        Client client = service.getClient(id);
        model.addAttribute("client",client);
        return "client_detail";
    }
    @GetMapping("/add")
    public String addClient(Model model,@ModelAttribute ClientDto clientDto){
        return "client_add";
    }
    @PostMapping("/save")
    public String saveClient(Model model, @ModelAttribute @Validated ClientDto clientDto,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return addClient(model,clientDto);
        }
        service.save(clientDto);
        return "redirect:/client/list";
    }
    @GetMapping("/edit/{id}")
    public String editClient(Model model,@PathVariable("id")Integer id,
                             @RequestParam("name")String name,@RequestParam("post_code")String postCode,
                             @RequestParam("address")String address,@RequestParam("phone")String telephoneNumber,
                             @RequestParam("fax")String fax,@RequestParam("note")String note){
        service.edit(create(id,name,postCode,address,telephoneNumber,fax,note));
        return "redirect:/client/{id}";
    }


    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id")Integer id){
        Client client = service.getClient(id);
        service.delete(client);
        return "redirect:/client/list";
    }
    private Client create(Integer id,String name, String postCode, String address,
                                String telephoneNumber, String fax,String note){
        Client c = service.getClient(id);
        c.setName(name);
        c.setPostCode(postCode);
        c.setAddress(address);
        c.setTelephoneNumber(telephoneNumber);
        c.setFax(fax);
        c.setNote(note);
        return c;
    }
}