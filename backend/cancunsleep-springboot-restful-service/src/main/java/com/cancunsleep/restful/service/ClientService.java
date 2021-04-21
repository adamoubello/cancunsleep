package com.cancunsleep.restful.service;

import com.cancunsleep.restful.model.Client;
import com.cancunsleep.restful.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client entity) {
        return clientRepository.save(entity);
    }

    @Override
    public Client getById(Serializable id){
        return clientRepository.getOne((Long) id);
    }

    @Override
    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        clientRepository.delete((Long) id);
    }
}
