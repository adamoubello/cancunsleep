package com.cancunsleep.restful.controller;

import com.cancunsleep.restful.model.Room;
import com.cancunsleep.restful.service.IRoomService;
import com.cancunsleep.restful.service.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/room")
public class RoomController {

    final static Logger logger = Logger.getLogger(RoomController.class);

    @Autowired
    IRoomService iRoomService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        iRoomService.save(room);
        logger.debug("Added:: " + room);
        return new ResponseEntity<Room>(room, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRoom(@RequestBody Room room) {
        Room existingRoom = iRoomService.getById(room.getIdroom());
        if (existingRoom == null) {
            logger.debug("Room with idroom " + room.getIdroom() + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            iRoomService.save(room);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{idroom}", method = RequestMethod.GET)
    public ResponseEntity<Room> getRoom(@PathVariable("idroom") Long idroom) {
        Room room = iRoomService.getById(idroom);
        if (room == null) {
            logger.debug("Room with idroom " + idroom + " does not exists");
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found Room:: " + room);
        return new ResponseEntity<Room>(room, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = iRoomService.getAll();
        if (rooms.isEmpty()) {
            logger.debug("Rooms does not exists");
            return new ResponseEntity<List<Room>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found " + rooms.size() + " Rooms");
        logger.debug(rooms);
        logger.debug(Arrays.toString(rooms.toArray()));
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
    }

    @RequestMapping(value = "/{idroom}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRoom(@PathVariable("idroom") Long idroom) {
        Room room = iRoomService.getById(idroom);
        if (room == null) {
            logger.debug("Room with id " + idroom + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            iRoomService.delete(idroom);
            logger.debug("Room with id " + idroom + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

}
