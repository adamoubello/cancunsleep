package com.cancunsleep.restful.controller;

import com.cancunsleep.restful.model.*;
import com.cancunsleep.restful.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.cancunsleep.restful.utils.Constants.DATE_PATTERN;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/booking")
public class BookingController {
    
    final static Logger logger = Logger.getLogger(BookingController.class);

    @Autowired
    private IRoomService iRoomService;

    @Autowired
    private IInnkeeperService iInnkeeperService;

    @Autowired
    private IClientService iClientService;

    @Autowired
    private IDateService iDateService;

    @Autowired
    private IBookingService iBookingService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> addBooking(@RequestBody BookingDto bookingDto) {
        //We check that the reservation is valide according to business rules
        if (iBookingService.validateBooking(bookingDto.getStaystart(), bookingDto.getStaystop())) {
            Booking booking = iBookingService.save(iBookingService.fromBookingDtoToBooking(bookingDto));
            logger.debug("Added:: " + booking);
            return new ResponseEntity<Booking>(booking, HttpStatus.CREATED);
        }
        //We log errors and their explanations
        logger.debug("Maximum 3 days stay exceeded " +
                "OR Maximum booking in advance time frame exceeded " +
                "OR Booking should start at least the next day:: " +
                bookingDto);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateBooking(@RequestBody BookingDto bookingDto) {
        //Booking objects mapping
        BookingId bookingId = new BookingId();
        bookingId.setIdroom(bookingDto.getIdroom());
        bookingId.setIdinnkeeper(bookingDto.getIdinnkeeper());
        bookingId.setIdclient(bookingDto.getIdclient());
        bookingId.setIddate(bookingDto.getIddate());

        Booking bookingExist = iBookingService.findByBookingId(bookingId);

        if (bookingExist == null) {
            logger.debug("Booking does not exist");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            //We check that the reservation is valide according to business rules
            if (!iBookingService.validateBooking(bookingDto.getStaystart(), bookingDto.getStaystop())) {
                logger.debug("Maximum 3 days stay exceeded " +
                        "OR Maximum booking in advance time frame exceeded " +
                        "OR Booking should start at least the next day:: " +
                        bookingDto);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            //We apply updates
            bookingExist.setComment(bookingDto.getComment());
            bookingExist.setBusied(bookingDto.isBusied());
            bookingExist.setAvailable(bookingDto.isAvailable());
            bookingExist.setDuration(bookingDto.getDuration());
            bookingExist.setStaystart(
                    iBookingService.dateToEpochConverter(bookingDto.getStaystart(), DATE_PATTERN));
            bookingExist.setStaystop(
                    iBookingService.dateToEpochConverter(bookingDto.getStaystop(), DATE_PATTERN));
            iBookingService.save(bookingExist);

            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<Booking> getBooking(@RequestBody BookingId bookingId) {

        Booking booking = iBookingService.findByBookingId(bookingId);

        if (booking == null) {
            logger.debug("Booking does not exist");
            return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found booking:: " + booking);
        return new ResponseEntity<Booking>(booking, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Booking>> getAllBookings() {

        List<Booking> bookings = iBookingService.getAll();

        if (bookings.isEmpty()) {
            logger.debug("Bookings does not exist");
            return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found " + bookings.size() + " Bookings");
        logger.debug(bookings);
        logger.debug(Arrays.toString(bookings.toArray()));
        return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBooking(@RequestBody BookingId bookingId) {

        Booking booking = new Booking();
        booking.setIdroom(iRoomService.getById(bookingId.getIdroom()));
        booking.setIdinnkeeper(iInnkeeperService.getById(bookingId.getIdinnkeeper()));
        booking.setIdclient(iClientService.getById(bookingId.getIdclient()));
        booking.setIddate(iDateService.getById(bookingId.getIddate()));

        if (iBookingService.existBookingId(bookingId)) {
            iBookingService.delete(booking);
            logger.debug("Booking deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}
