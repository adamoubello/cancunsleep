package com.cancunsleep.restful.service;

import com.cancunsleep.restful.model.*;
import com.cancunsleep.restful.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import static com.cancunsleep.restful.utils.Constants.*;

@Service
@Transactional
public class BookingService implements IBookingService {

    @Autowired
    private EntityManager bookingEntityManager;

    @Autowired
    private BookingRepository bookingRepository;

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

    public Booking save(Booking entity) {
        bookingEntityManager.persist(entity);
        return entity;
    }

    @Override
    public Booking findByBookingId (BookingId bookingId) {
        return bookingRepository.findOne(bookingId);
    }

    //We check the presence of bookingId record in database
    public boolean existBookingId (BookingId bookingId) {
        return bookingRepository.exists(bookingId);
    }

    @Override
    public List<Booking> getAll(){
        List<Booking> list = new ArrayList<>();
        bookingRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(Booking id) {
        bookingRepository.delete(id);
    }

    //We create a Date object with current System date and time
    private Date createNowDate() {
        Date date = new Date();
        date.setEpochdate(getInstantDate());
        date.setDate(formatLocaleDateTime(LocalDateTime.now()));
        return iDateService.save(date);
    }

    //Retrieve current Instant in epoch time
    private long getInstantDate () {
        return Instant.now().toEpochMilli();
    }

    //We convert date from string to epoch in specified pattern
    public long dateToEpochConverter(String s, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).withZone(ZoneOffset.UTC)
                .parse(s, p -> p.getLong(ChronoField.INSTANT_SECONDS));
    }

    //We convert date from string to LocaleDateTime in specified pattern
    private LocalDateTime dateToLocaleDateTimeConverter(String strDate) {
        return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    //We convert date from string to LocaleDate in specified pattern
    private LocalDate dateToLocaleDateConverter(String strDate) {
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    //Get the current date and time in LocaleDateTime object for specified timezone (UTC)
    private LocalDateTime getCurrentLocaleDateTime () {
        return LocalDateTime.from(Instant.now().atZone(ZoneId.of(DATE_ZONE)));
    }

    //Add specified number of days to specified LocalDateTime
    private LocalDateTime nextDayComputer(LocalDateTime localDateTime, long nbr) {
        return LocalDateTime.from(localDateTime.atZone(ZoneId.of(DATE_ZONE))).plusDays(nbr);
    }

    //Format any LocaleDateTime in specified pattern
    private String formatLocaleDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    //Check that bookings are not placed out of business rule frames
    private boolean maxLimitChecker(String start, String stop, long nbr) {
        return !dateToLocaleDateTimeConverter(stop).isAfter(nextDayComputer(dateToLocaleDateTimeConverter(start), nbr));
    }

    //Prevent booking made same day of first stay date
    private boolean minimumBookingChecker(LocalDate todayDate, LocalDate bookingDate) {
        return todayDate.isBefore(bookingDate);
    }

    //Business rules validator
    public boolean validateBooking(String staystart, String staystop) {
        return maxLimitChecker(staystart, staystop, STAY_LIMIT)
                && maxLimitChecker(formatLocaleDateTime(getCurrentLocaleDateTime()), staystart, BOOKING_ADVANCE_LIMIT)
                && minimumBookingChecker(LocalDate.now(), dateToLocaleDateConverter(staystart));
    }

    //BookingDto to Booking mapper to ease db saving
    public Booking fromBookingDtoToBooking(BookingDto bookingDto) {

        Room room = iRoomService.getById(bookingDto.getIdroom());
        Innkeeper innkeeper = iInnkeeperService.getById(bookingDto.getIdinnkeeper());
        Client client = iClientService.getById(bookingDto.getIdclient());

        Booking booking = new Booking();
        booking.setIdroom(room);
        booking.setIdinnkeeper(innkeeper);
        booking.setIdclient(client);
        booking.setIddate(createNowDate());
        booking.setAvailable(bookingDto.isAvailable());
        booking.setStaystart(dateToEpochConverter(bookingDto.getStaystart(), DATE_PATTERN));
        booking.setStaystop(dateToEpochConverter(bookingDto.getStaystop(), DATE_PATTERN));
        booking.setDuration(bookingDto.getDuration());
        booking.setBusied(bookingDto.isBusied());
        booking.setComment(bookingDto.getComment());

        return booking;
    }

}
