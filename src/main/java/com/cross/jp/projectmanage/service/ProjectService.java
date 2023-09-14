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
    @Autowired
    OrderRepository orderRepository;

    public void save(ProjectDto dto){
        Project project = createProject(dto);
        projectRepository.save(project);
        Project getProject = getTop1(dto.getClientId());
        orderRepository.save(createOrder(dto,getProject));
    }
    private Project getTop1(Integer id){
        List<Project> projects = projectRepository.getSaveProject(id);
        return projects.get(0);
    }
    private Project createProject(ProjectDto dto){
        Project project = new Project();

        project.setManager(dto.getManager());
        project.setProgress(dto.getProgress());
        project.setDeliveryDate(dto.getDeliveryDate());
        project.setReceptionDate(dto.getReceptionDate());

        project.setClient(clientRepository.getByIdClient(dto.getClientId()));
        return project;
    }
    private Order createOrder(ProjectDto dto,Project p){
        Order order = new Order();

        order.setAmount(dto.getAmount());
        order.setQuantity(dto.getQuantity());
        order.setItemCategory(dto.getItem());

        return order;
    }
}
