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
        int allAmount = allAmount(orders);
        model.addAttribute("date",date);
        model.addAttribute("order",orders);
        model.addAttribute("item",CategoryMap.items);
        model.addAttribute("allAmount",allAmount);
        return "proceeds";
    }
    @GetMapping("/search")
    public String searchProceeds(Model model, @RequestParam("date")String date,
                                 @RequestParam("item")Integer item){
        List<Order> orders = service.search(date,item);

        model.addAttribute("order",orders);
        model.addAttribute("date",date);
        model.addAttribute("itemCategory",item);
        model.addAttribute("allAmount",allAmount(orders));
        model.addAttribute("item",CategoryMap.items);
        return "proceeds";
    }

    /**
     *現在日時を文字列で習得（例 2022-02)
     */
    private String nowDate(){
        Calendar calendar = Calendar.getInstance(); //現在日時を取得
        Date date = calendar.getTime();
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy/MM");
        String nowDate = sformat.format(date);
        return nowDate.replace('/','-');
    }

    /**
     *売上をすべて足して返す
     */
    private int allAmount(List<Order> orders){
        int amount = 0;
        for(Order order : orders){
            amount += order.getAmount();
        }
        return amount;
    }
}
