package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.config.SortByDate;
import com.cross.jp.projectmanage.dto.ClientDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        List<Order> orders = client.getOrders();
        List<Order> proceeds = sortOrders(orders);
        model.addAttribute("client",client);
        model.addAttribute("proceeds",proceeds);
        model.addAttribute("item", CategoryMap.items);
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
        if(clientDto.getId() != null){
            Integer id = clientDto.getId();
            service.edit(clientDto);
            return clientDetail(model,id);
        }
        service.save(clientDto);
        return "redirect:/client/list";
    }
    @GetMapping("/edit/{id}")
    public String editClient(Model model,@PathVariable("id")Integer id,
                             @ModelAttribute ClientDto dto){
        Client c = service.getClient(id);
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setTelephoneNumber(c.getTelephoneNumber());
        dto.setFax(c.getFax());
        dto.setAddress(c.getAddress());
        dto.setPostCode(c.getPostCode());
        dto.setNote(c.getNote());
        return "client_add";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id")Integer id){
        Client client = service.getClient(id);
        service.delete(client);
        return "redirect:/client/list";
    }

    /**
     *納品済みのみを選別して日付順に並び変える
     */
    private List<Order> sortOrders(List<Order> orders){
        List<Order> proceeds = new ArrayList<>();
        for(Order o : orders){
            if(o.getEndCheck()){
                proceeds.add(o);
            }
        }
        proceeds.sort(new SortByDate());
        return proceeds;
    }
}
