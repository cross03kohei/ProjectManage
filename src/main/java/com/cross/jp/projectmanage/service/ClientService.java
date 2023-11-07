package com.cross.jp.projectmanage.service;

import com.cross.jp.projectmanage.dto.ClientDto;
import com.cross.jp.projectmanage.entity.Client;
import com.cross.jp.projectmanage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    public List<Client> getClientList(){
        return clientRepository.findAll();
    }
    public Client getClient(Integer id){return clientRepository.getByIdClient(id);}
    public List<Client> searchClient(String name){return clientRepository.getByNameClientList(name);}

    public void save(ClientDto clientDto){
        clientRepository.save(createClient(clientDto));
    }
    public void edit(ClientDto dto){ clientRepository.save(editClient(dto));}
    public void delete(Client client){ clientRepository.delete(client);}
    private Client createClient(ClientDto c){
        Client client = new Client();
        client.setName(c.getName());
        client.setPostCode(c.getPostCode());
        client.setAddress(c.getAddress());
        client.setTelephoneNumber(c.getTelephoneNumber());
        client.setFax(c.getFax());
        client.setNote(c.getNote());
        return client;
    }
    private Client editClient(ClientDto dto){
        Client c = getClient(dto.getId());
        c.setName(dto.getName());
        c.setPostCode(dto.getPostCode());
        c.setAddress(dto.getAddress());
        c.setTelephoneNumber(dto.getTelephoneNumber());
        c.setFax(dto.getFax());
        c.setNote(dto.getNote());
        return c;
    }
}
