package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/proceeds")
public class ProceedsController {
    @Autowired
    OrderService service;

    @GetMapping("/list")
    public String proceedsList(Model model){
        String date = nowDate();
        List<Order> orders = service.getByMouthProceeds(date);
        model.addAttribute("date",date);
        model.addAttribute("order",orders);
        model.addAttribute("item",CategoryMap.items);
        return "proceeds";
    }
    @GetMapping("/search")
    public String searchProceeds(Model model, @RequestParam("date")String date,
                                 @RequestParam("item")Integer item){

        model.addAttribute("date",date);
        model.addAttribute("item",CategoryMap.items);
        return "proceeds";
    }
    private String nowDate(){
        Calendar calendar = Calendar.getInstance(); //現在日時を取得
        Date date = calendar.getTime();
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy/MM");
        String nowDate = sformat.format(date);
        return nowDate.replace('/','-');
    }
}
