package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.dto.EndCheckDto;
import com.cross.jp.projectmanage.dto.ProgressDto;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    ClientService clientService;
    @Autowired
    OrderService service;

    @GetMapping("/list")
    public String projectList(Model model, @ModelAttribute EndCheckDto endCheckDto,
                              @ModelAttribute ProgressDto progressDto){
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
                if(project.getEndCheck()){
                    continue;
                }
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


    @GetMapping("/progress")
    public String editProject(@ModelAttribute @Validated ProgressDto progressDto,
                              BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return errorProgress(model,progressDto);
        }
        service.saveProgress(createProject(progressDto));
        return "redirect:/project/list";
    }
    @PostMapping("/check")
    public String checkProject(Model model,@ModelAttribute @Validated EndCheckDto dto,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return errorEnd(model,dto);
        }
        Order o = service.findById(dto.getId());
        o.setDeliveryDate(dto.getDate());
        o.setEndCheck(dto.getEndCheck());
        o.setProgress(dto.getProgress());
        service.edit(o);
        return "redirect:/project/list";
    }
    @GetMapping("/add")
    public String addProject(Model model,@ModelAttribute ProjectDto projectDto){
        projectDto.setReceptionDate(nowDate());
        model.addAttribute("items",CategoryMap.items);
        model.addAttribute("managers",CategoryMap.manager);
        model.addAttribute("progress",CategoryMap.progress);
        return "project_add";
    }
    @PostMapping(value = "/save")
    public String saveProject(@ModelAttribute @Validated ProjectDto dto,
                              BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return addProject(model,dto);
        }
        service.save(dto);
        return "redirect:/project/list";
    }
    @GetMapping("errorEnd")
    public String errorEnd(Model model,@ModelAttribute EndCheckDto endCheckDto){
        return "error";
    }
    @GetMapping("errorProgress")
    public String errorProgress(Model model, @ModelAttribute ProgressDto progressDto){
        return "errorProgress";
    }
    @RequestMapping(value = "/delete")
    public String deleteProject(@RequestParam("id")Integer id){
        service.delete(service.findById(id));
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
        json.setProgress(o.getProgress());
        json.setDeliveryDate(o.getDeliveryDate());
        return json;
    }

    /**
     *ProgerssDtoをOrderに変換するメソッド
     * */
    private Order createProject(ProgressDto dto){
        Order o = service.findById(dto.getId());
        o.setItemCategory(dto.getItem());
        o.setQuantity(dto.getQuantity());
        o.setAmount(dto.getAmount());
        o.setManager(dto.getManager());
        o.setProgress(dto.getProgress());
        return o;
    }

    /**
     *現在日時を所得 受付日に値を持たせるためにつかう
     */
    private String nowDate(){
        Calendar calendar = Calendar.getInstance(); //現在日時を取得
        Date date = calendar.getTime();
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy/MM/dd");
        String nowDate = sformat.format(date);
        return nowDate.replace('/','-');
    }

}
