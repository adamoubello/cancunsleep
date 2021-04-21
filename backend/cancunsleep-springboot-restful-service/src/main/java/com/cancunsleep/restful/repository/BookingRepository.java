package com.cancunsleep.restful.repository;

import com.cancunsleep.restful.model.Booking;
import com.cancunsleep.restful.model.BookingId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, BookingId> {
}
