package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.BookingRepository;
import com.virtualpairprogrammers.roombooking.model.entities.Booking;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/bookings")
@AllArgsConstructor
public class RestBookingController {

    private final BookingRepository bookingRepository;

    @GetMapping(path = "/{date}")
    public List<Booking> getBookingByDate(@PathVariable String date) throws InterruptedException {
        Thread.sleep(3000);
        Date sqlDate = Date.valueOf(date);

        return  bookingRepository.findAllByDate(sqlDate);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBooking(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(5000);
        bookingRepository.deleteById(id);
    }

    @GetMapping
    public Booking getBookingById(@RequestParam Long id) throws InterruptedException {
        Thread.sleep(3000);

        return  bookingRepository.findById(id).get();
    }
}
