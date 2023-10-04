package com.cross.jp.projectmanage.controller;

import com.cross.jp.projectmanage.CategoryMap;
import com.cross.jp.projectmanage.config.SortByDate;
import com.cross.jp.projectmanage.dto.ProceedsDto;
import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.form.CheckForm;
import com.cross.jp.projectmanage.service.OrderService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        orders.sort(new SortByDate());
        model.addAttribute("order",orders);
        model.addAttribute("date",date);
        model.addAttribute("itemCategory",item);
        model.addAttribute("allAmount",allAmount(orders));
        model.addAttribute("item",CategoryMap.items);
        return "proceeds";
    }
    /**
     * 未入金の売上を表示する
     */
    @GetMapping("/payment")
    public String isPaymentProceeds(Model model){
        List<Order> orders = service.isPaymentFalse();
        orders.sort(new SortByDate());
        model.addAttribute("order",orders);
        model.addAttribute("allAmount",allAmount(orders));
        model.addAttribute("item", CategoryMap.items);
        return "proceeds";
    }
    @GetMapping("/edit/{id}")
    public String editByIdProceeds(Model model, @PathVariable("id")Integer id){
        Order o = service.findById(id);
        model.addAttribute("proceedsDto",createDto(o));
        model.addAttribute("items",CategoryMap.items);
        return "proceeds_edit";
    }
    @GetMapping("/edit")
    public String editProceeds(Model model, @ModelAttribute ProceedsDto dto){
        model.addAttribute("items",CategoryMap.items);
        return "proceeds_edit";
    }
    @PostMapping("/back")
    public String backProceeds(Model model,@ModelAttribute @Validated ProceedsDto dto,
                               BindingResult bindingResult){
        Order o = service.findById(dto.getId());
        o.setPaymentCheck(false);
        o.setEndCheck(false);
        o.setProgress(0);   //進行中の0は未着手
        service.saveProgress(o);
        return proceedsList(model);
    }
    @PostMapping("/edit")
    public String addProceeds(Model model,
                              @ModelAttribute @Validated ProceedsDto dto,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return editProceeds(model,dto);
        }
        Order order = createOrder(dto);
        service.saveProgress(order);
        return "redirect:/proceeds/list";
    }
    @PostMapping("/check")
    @ResponseBody
    public String checkProceeds(CheckForm form){
        List<Integer> listId = form.getListId();
        List<Integer> checkId = form.getCheckId();
        /*
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

    /**
     *ドメインクラスを作成する　一番最初の編集画面用
     */
    private ProceedsDto createDto(Order order){
        ProceedsDto dto = new ProceedsDto();
        dto.setId(order.getId());
        dto.setItem(order.getItemCategory());
        dto.setQuantity(order.getQuantity());
        dto.setAmount(order.getAmount());
        dto.setDeliveryDate(order.getDeliveryDate());
        return dto;
    }

    /**
     *ドメインクラスからEntityクラスに変換する　編集用
     */
    private Order createOrder(ProceedsDto dto){
        Order o = service.findById(dto.getId());
        o.setQuantity(dto.getQuantity());
        o.setItemCategory(dto.getItem());
        o.setAmount(dto.getAmount());
        o.setDeliveryDate(dto.getDeliveryDate());
        return o;
    }

}
