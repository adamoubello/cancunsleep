package com.cancunsleep.restful.controller;

import com.cancunsleep.restful.model.Date;
import com.cancunsleep.restful.service.DateService;
import com.cancunsleep.restful.service.IDateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/date")
public class DateController {

    final static Logger logger = Logger.getLogger(DateController.class);

    @Autowired
    IDateService iDateService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Date> addDate(@RequestBody Date date) {
        iDateService.save(date);
        logger.debug("Added:: " + date);
        return new ResponseEntity<Date>(date, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateDate(@RequestBody Date date) {
        Date existingDate = iDateService.getById(date.getIddate());
        if (existingDate == null) {
            logger.debug("Date with iddate " + date.getIddate() + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            iDateService.save(date);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{iddate}", method = RequestMethod.GET)
    public ResponseEntity<Date> getDate(@PathVariable("iddate") Long iddate) {
        Date date = iDateService.getById(iddate);
        if (date == null) {
            logger.debug("Date with iddate " + iddate + " does not exists");
            return new ResponseEntity<Date>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found Date:: " + date);
        return new ResponseEntity<Date>(date, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Date>> getAllDates() {
        List<Date> dates = iDateService.getAll();
        if (dates.isEmpty()) {
            logger.debug("Dates does not exists");
            return new ResponseEntity<List<Date>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found " + dates.size() + " Dates");
        logger.debug(dates);
        logger.debug(Arrays.toString(dates.toArray()));
        return new ResponseEntity<List<Date>>(dates, HttpStatus.OK);
    }

    @RequestMapping(value = "/{iddate}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDate(@PathVariable("iddate") Long iddate) {
        Date date = iDateService.getById(iddate);
        if (date == null) {
            logger.debug("Date with id " + iddate + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            iDateService.delete(iddate);
            logger.debug("Date with id " + iddate + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

}
