package com.cross.jp.projectmanage.service;

import com.cross.jp.projectmanage.dto.ProjectDto;
import com.cross.jp.projectmanage.entity.Order;
import com.cross.jp.projectmanage.entity.Project;
import com.cross.jp.projectmanage.repository.ClientRepository;
import com.cross.jp.projectmanage.repository.OrderRepository;
import com.cross.jp.projectmanage.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ClientRepository clientRepository;

    public void save(ProjectDto dto){
        Project project = createProject(dto);
        projectRepository.save(project);
    }
    private Project createProject(ProjectDto dto){
        Project project = new Project();
        Order order = new Order();

        project.setManager(dto.getManager());
        project.setProgress(dto.getProgress());
        project.setDeliveryDate(dto.getDeliveryDate());
        project.setReceptionDate(dto.getReceptionDate());

        order.setAmount(dto.getAmount());
        order.setQuantity(dto.getQuantity());
        order.setItemCategory(dto.getItem());
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        project.setOrders(orders);
        project.setClient(clientRepository.getByIdClient(dto.getClientId()));
        return project;
    }
}
