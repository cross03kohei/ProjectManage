package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.form.SearchForm;
import com.cross.jp.projectmanage.service.ClientService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    ClientService clientService;
    @GetMapping("/list")
    public String projectList(Model model){
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
    @PostMapping(value = "json")
    @ResponseBody
    public String getJsonData(SearchForm form){
        String name = form.getName();
        List<Client> clients = clientService.searchClient(name);
        Gson gson = new Gson();
        return gson.toJson(clients);
    }

}
