package com.flightapp.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import com.flightapp.exceptions.ValidationException;
import com.flightapp.exceptions.ResourceNotFoundException;
import com.flightapp.model.Booking;
import com.flightapp.repository.BookingRepository;

@Service
public class TicketService {

    private final BookingRepository bookingRepository;

    public TicketService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Mono<Booking> getTicketByPnr(String pnr) {

        if (pnr == null || pnr.trim().isEmpty())
            throw new ValidationException("PNR cannot be empty");

        if (pnr.length() != 6)
            throw new ValidationException("PNR must be exactly 6 characters");

        if (!pnr.matches("^[A-Z0-9]+$"))
            throw new ValidationException("PNR must be alphanumeric");

        return bookingRepository.findByPnrOutbound(pnr)
                .switchIfEmpty(
                        bookingRepository.findByPnrReturn(pnr)
                                .switchIfEmpty(Mono.error(new ResourceNotFoundException("PNR not found")))
                );
    }
}
