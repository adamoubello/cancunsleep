package com.cancunsleep.restful.service;

import com.cancunsleep.restful.model.Innkeeper;
import com.cancunsleep.restful.repository.InnkeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class InnkepperService implements IInnkeeperService {

    @Autowired
    private InnkeeperRepository innkeeperRepository;

    @Override
    public Innkeeper save(Innkeeper entity) {
        return innkeeperRepository.save(entity);
    }

    @Override
    public Innkeeper getById(Serializable id){ return innkeeperRepository.getOne((Long) id); }

    @Override
    public List<Innkeeper> getAll(){
        return innkeeperRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        innkeeperRepository.delete((Long) id);
    }

}
