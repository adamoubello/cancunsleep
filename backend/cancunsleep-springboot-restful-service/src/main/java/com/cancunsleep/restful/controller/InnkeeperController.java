package com.cancunsleep.restful.controller;

import com.cancunsleep.restful.model.Innkeeper;
import com.cancunsleep.restful.model.Room;
import com.cancunsleep.restful.service.IInnkeeperService;
import com.cancunsleep.restful.service.InnkepperService;
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
@RequestMapping("/innkeeper")
public class InnkeeperController {

    final static Logger logger = Logger.getLogger(InnkeeperController.class);

    @Autowired
    IInnkeeperService iInnkeeperService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Innkeeper> addInnkeeper(@RequestBody Innkeeper innkeeper) {
        iInnkeeperService.save(innkeeper);
        logger.debug("Added:: " + innkeeper);
        return new ResponseEntity<Innkeeper>(innkeeper, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateInnkeeper(@RequestBody Innkeeper innkeeper) {
        Innkeeper existingInnkeeper = iInnkeeperService.getById(innkeeper.getIdinnkeeper());
        if (existingInnkeeper == null) {
            logger.debug("Innkeeper with idinnkeeper " + innkeeper.getIdinnkeeper() + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            iInnkeeperService.save(innkeeper);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{idinnkeeper}", method = RequestMethod.GET)
    public ResponseEntity<Innkeeper> getInnkeeper(@PathVariable("idinnkeeper") Long idinnkeeper) {
        Innkeeper innkeeper = iInnkeeperService.getById(idinnkeeper);
        if (innkeeper == null) {
            logger.debug("Innkeeper with idinnkeeper " + idinnkeeper + " does not exists");
            return new ResponseEntity<Innkeeper>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found Innkeeper:: " + innkeeper);
        return new ResponseEntity<Innkeeper>(innkeeper, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Innkeeper>> getAllInnkeepers() {
        List<Innkeeper> innkeepers = iInnkeeperService.getAll();
        if (innkeepers.isEmpty()) {
            logger.debug("Innkeepers does not exists");
            return new ResponseEntity<List<Innkeeper>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found " + innkeepers.size() + " Innkeepers");
        logger.debug(innkeepers);
        logger.debug(Arrays.toString(innkeepers.toArray()));
        return new ResponseEntity<List<Innkeeper>>(innkeepers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{idinnkeeper}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteInnkeeper(@PathVariable("idinnkeeper") Long idinnkeeper) {
        Innkeeper innkeeper = iInnkeeperService.getById(idinnkeeper);
        if (innkeeper == null) {
            logger.debug("Innkeeper with id " + idinnkeeper + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            iInnkeeperService.delete(idinnkeeper);
            logger.debug("Innkeeper with id " + idinnkeeper + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

}
