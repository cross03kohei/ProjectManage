package com.cross.jp.projectmanage.service;

import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.repository.ClientRepository;
import com.cross.jp.projectmanage.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;

    public void save(ProjectDto dto){
        orderRepository.save(createOrder(dto));
    }
    private Order createOrder(ProjectDto dto){
        Order o = new Order();
        if(dto.getClientId() != null){
            Client c = clientRepository.getByIdClient(dto.getClientId());
            o.setClient(c);
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
