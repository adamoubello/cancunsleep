package com.cancunsleep.restful.service;

import com.cancunsleep.restful.model.Room;
import com.cancunsleep.restful.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room save(Room entity) {
        return roomRepository.save(entity);
    }

    @Override
    public Room getById(Serializable id){ return roomRepository.getOne((Long) id); }

    @Override
    public List<Room> getAll(){
        return roomRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        roomRepository.delete((Long) id);
    }
}
