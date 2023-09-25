package com.cross.jp.projectmanage.service;

import com.cross.jp.projectmanage.ProjectSpecification;
import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.repository.ClientRepository;
import com.cross.jp.projectmanage.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;


    public List<Order> findAll(){ return orderRepository.findAll();}

    /**
     *現在の日付かつ納品済み（真偽型）のプロジェクトを取得
     */
    public List<Order> getByMouthProceeds(String date){
        ProjectSpecification spec = new ProjectSpecification();
        return orderRepository.findAll(Specification.where(spec.deliveryDateContains(date).
                and(spec.checkContains(true))));
    }
    public Order findById(Integer id){ return orderRepository.getByIdOrder(id);}
    public List<Order> search(String date,Integer item){    //item = 10 すべての商品のキー
        ProjectSpecification spec = new ProjectSpecification();
        if(date.equals("") && item != 10){  //dateが空で商品が選択されている場合
            return orderRepository.findAll(spec.itemContains(item).
                    and(spec.checkContains(true)));
        }
        if(!date.equals("") && item == 10){     //日付があり、商品が選択されていない場合
            return orderRepository.findAll(spec.deliveryDateContains(date).
                    and(spec.checkContains(true)));
        }
        if(!date.equals("")){       //どちらとも選択済み
            return orderRepository.findAll(spec.deliveryDateContains(date).
                    and(spec.itemContains(item).and(spec.checkContains(true))));
        }
        return orderRepository.findAll(spec.checkContains(true));   //何も選択されていない場合
    }

    public void save(ProjectDto dto){
        orderRepository.save(createOrder(dto));
    }
    public void delete(Order order){
        orderRepository.delete(order);
    }
    public void edit(Order order){ orderRepository.save(order);}
    private Order createOrder(ProjectDto dto){
        Order o = new Order();
        if(dto.getClientId() != null){
            Client c = clientRepository.getByIdClient(dto.getClientId());
            o.setClient(c);
        }else{
            Client c = new Client();        //clientのidがなければ先にclientの保存を行う
            c.setName(dto.getClientName());
            clientRepository.save(c);
            List<Client> clientList = clientRepository.findAll();
            Integer id = clientList.get(clientList.size() -1).getId();
            o.setClient(clientRepository.getByIdClient(id));
        }
        o.setItemCategory(dto.getItem());
        o.setQuantity(dto.getQuantity());
        o.setAmount(dto.getAmount());
        o.setManager(dto.getManager());
        o.setProgress(dto.getProgress());
        o.setEndCheck(false);
        o.setDeliveryDate(dto.getDeliveryDate());
        o.setReceptionDate(dto.getReceptionDate());
        return o;
    }
}
