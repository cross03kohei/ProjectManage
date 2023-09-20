package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.form.SearchForm;
import com.cross.jp.projectmanage.json.ClientJson;
import com.cross.jp.projectmanage.json.ProjectJson;
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
    @GetMapping("/progress")
    public String editProject(@RequestParam("id")Integer id,@RequestParam("item")Integer item,
                              @RequestParam("quantity")Integer quantity,@RequestParam("amount")Integer amount,
                              @RequestParam("manager")Integer manager,@RequestParam("progress")Integer progress){
        service.edit(createProject(id,item,quantity,amount,manager,progress));
        return "redirect:/project/list";
    }
    @GetMapping("/check")
    public String checkProject(@RequestParam("id")Integer id,
                              @RequestParam("progress")Integer progress,
                              @RequestParam("endCheck")Boolean endCheck){
        Order o = service.findById(id);
        if(progress == 2 && endCheck){
            o.setEndCheck(true);
        }else{
            o.setProgress(progress);
        }
        service.edit(o);
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
        List<ClientJson> json = new ArrayList<>(); //json用のリスト
        String name = form.getName();
        List<Client> clients = clientService.searchClient(name);
        //無限参照でスタックエラーがおこるのでJson用の型のリストに格納
        for (Client client : clients) {
            ClientJson c = new ClientJson();
            c.setId(client.getId());
            c.setName(client.getName());
            json.add(c);
        }
        Gson gson = new Gson();
        return gson.toJson(json);
    }
    @PostMapping(value = "edit")
    @ResponseBody
    public String copyValue(String id){
        Integer orderId = Integer.parseInt(id);
        ProjectJson json = createJson(service.findById(orderId));
        Gson gson = new Gson();
        return gson.toJson(json);
    }

    private ProjectJson createJson(Order o){
        ProjectJson json = new ProjectJson();
        Client c = o.getClient();
        json.setId(o.getId());
        json.setName(c.getName());
        json.setItem(o.getItemCategory());
        json.setQuantity(o.getQuantity());
        json.setAmount(o.getAmount());
        json.setManager(o.getManager());
        return json;
    }
    private Order createProject(Integer id,Integer item,Integer quantity,
                                 Integer amount,Integer manager,Integer progress){
        Order o = service.findById(id);
        o.setItemCategory(item);
        o.setQuantity(quantity);
        o.setAmount(amount);
        o.setManager(manager);
        o.setProgress(progress);
        return o;
    }

}
