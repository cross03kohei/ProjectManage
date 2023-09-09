package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {
    @GetMapping("/list")
    public String projectList(Model model){
        return "index";
    }
    @GetMapping("/add")
    public String addProject(Model model){
        model.addAttribute("item",CategoryMap.items);
        return "project_add";
    }
}
