package com.cross.jp.projectmanage.service;

import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.repository.ClientRepository;
import com.cross.jp.projectmanage.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Order> getByMouthProceeds(String date){
        String nowDate = date.replace('/','-');
        return orderRepository.getByMouthOrder(nowDate);
    }
    public Order findById(Integer id){ return orderRepository.getByIdOrder(id);}

    public void save(ProjectDto dto){
        orderRepository.save(createOrder(dto));
    }
    private Order createOrder(ProjectDto dto){
        Order o = new Order();
        if(dto.getClientId() != null){
            Client c = clientRepository.getByIdClient(dto.getClientId());
            o.setClient(c);
        }else{
            Client c = new Client();
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
        o.setDeliveryDate(dto.getDeliveryDate());
        o.setReceptionDate(dto.getReceptionDate());
        return o;
    }
}
