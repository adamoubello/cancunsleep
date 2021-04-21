package com.cancunsleep.restful.service;

import com.cancunsleep.restful.model.Date;
import com.cancunsleep.restful.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


@Service
public class DateService implements IDateService {

    @Autowired
    private DateRepository dateRepository;

    @Override
    public Date save(Date entity) {
        return dateRepository.save(entity);
    }

    @Override
    public Date getById(Serializable id){ return dateRepository.getOne((Long) id); }

    @Override
    public List<Date> getAll(){
        return dateRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        dateRepository.delete((Long) id);
    }
}
