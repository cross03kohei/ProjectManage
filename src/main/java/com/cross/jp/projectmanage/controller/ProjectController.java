package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.dto.ProjectDto;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {
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
    @RequestMapping(value = "json",method = RequestMethod.GET)
    @ResponseBody
    public String getJsonData(ModelMap model){
        Gson gson = new Gson();
        return gson.toJson("test");
    }

}
