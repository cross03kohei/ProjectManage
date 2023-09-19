package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.form.SearchForm;
import com.cross.jp.projectmanage.service.ClientService;
import com.cross.jp.projectmanage.service.OrderService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    ClientService clientService;
    @Autowired
    OrderService service;

    @GetMapping("/list")
    public String projectList(Model model){
        List<Order> noProject = new ArrayList<>();
        List<Order> ingProject = new ArrayList<>();
        List<Order> endProject = new ArrayList<>();
        List<Order> projects = service.findAll();
        /**
         * 進行状態によって各Listに配置していく
         */
        for (Order project : projects) {
            if (project.getProgress() == 0) {
                noProject.add(project);
            }
            if (project.getProgress() == 1) {
                ingProject.add(project);
            }
            if (project.getProgress() == 2) {
                endProject.add(project);
            }
        }
        model.addAttribute("no",noProject);
        model.addAttribute("ing",ingProject);
        model.addAttribute("end",endProject);
        model.addAttribute("item",CategoryMap.items);  //itemと担当者を表示するため
        model.addAttribute("manager",CategoryMap.manager);
        model.addAttribute("progress",CategoryMap.progress);
        return "index";
    }

    @GetMapping("/add")
    public String addProject(Model model){
        model.addAttribute("projectDto",new ProjectDto());
        model.addAttribute("items",CategoryMap.items);
        model.addAttribute("managers",CategoryMap.manager);
        model.addAttribute("progress",CategoryMap.progress);
        return "project_add";
    }
    @GetMapping("/endCheck")
    public String editProject(@RequestParam("id")Integer id,
                              @RequestParam("progress")Integer progress,
                              @RequestParam("endCheck")Boolean endCheck){
        if(progress == 2 && endCheck != null){
            Order o = service.findById(id);
            o.setEndCheck(true);
            service.edit(o);
        }else{
            Order o = service.findById(id);
            o.setProgress(progress);
            service.edit(o);
        }
        return "redirect:/project/list";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute ProjectDto dto){
        service.save(dto);
        return "redirect:/project/list";
    }
    @PostMapping(value = "json")
    @ResponseBody
    public String getJsonData(SearchForm form){
        String name = form.getName();
        List<Client> clients = clientService.searchClient(name);
        Gson gson = new Gson();
        return gson.toJson(clients);
    }
    @PostMapping(value = "edit")
    @ResponseBody
    public String copyValue(String id){
        Integer orderId = Integer.parseInt(id);
        Order order = service.findById(orderId);
        Gson gson = new Gson();
        return gson.toJson(order);
    }

}
