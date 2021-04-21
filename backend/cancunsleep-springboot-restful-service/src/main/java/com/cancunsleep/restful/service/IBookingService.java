package com.cancunsleep.restful.service;

import com.cancunsleep.restful.model.Booking;
import com.cancunsleep.restful.model.BookingDto;
import com.cancunsleep.restful.model.BookingId;
import java.util.List;

public interface IBookingService {
    Booking findByBookingId(BookingId bookingId);
    Booking save(Booking booking);
    List<Booking> getAll();
    void delete(Booking id);
    Booking fromBookingDtoToBooking(BookingDto bookingDto);
    boolean existBookingId (BookingId bookingId);
    long dateToEpochConverter(String strDate, String pattern);
    boolean validateBooking(String staystart, String staystop);
}
