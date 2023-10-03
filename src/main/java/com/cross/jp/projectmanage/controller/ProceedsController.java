package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.form.CheckForm;
import com.cross.jp.projectmanage.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;


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
    @PostMapping("/check")
    @ResponseBody
    public String checkProceeds(CheckForm form){
        List<Integer> listId = form.getListId();
        List<Integer> checkId = form.getCheckId();
        /**
         *  とてもダメなコード　idを比較して等しければpaymentをtrueにする
         */
        if(checkId != null){
            for(Integer x : listId){
                for (Integer y : checkId){
                    if (x.equals(y)){
                        Order o = service.findById(x);
                        o.setPaymentCheck(true);
                        service.saveProgress(o);
                        break;
                    }
                    Order o = service.findById(x);
                    o.setPaymentCheck(false);
                    service.saveProgress(o);
                }
            }
        }else{
            if(listId != null){
                for(Integer id : listId){
                    Order o = service.findById(id);
                    o.setPaymentCheck(false);
                    service.saveProgress(o);
            }
            }else{
                return "更新できませんでした";
            }
        }
        return "更新しました";
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
