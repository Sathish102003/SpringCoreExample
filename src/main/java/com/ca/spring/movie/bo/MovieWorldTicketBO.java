package com.ca.spring.movie.bo;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ca.spring.movie.dao.MovieWorldTicketDAO;
import com.ca.spring.movie.exception.MovieWorldException;
import com.ca.spring.movie.vo.TicketVO;

@Component
public class MovieWorldTicketBO {

    private static final int NO_CHARGE = 0;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MovieWorldTicketDAO movieWorldTicketDAO;

    @Autowired
    private Map<String, Integer> parkingCharges;

    @Transactional(value = "txManager", propagation = Propagation.REQUIRED)
    public int reserveSeats(TicketVO ticketVO) throws MovieWorldException {
        movieWorldTicketDAO.saveReservationDetails(ticketVO);
        movieWorldTicketDAO.updateAvailableSeats(ticketVO);
        final Integer oneTicketPrice = Integer
                .valueOf(getTicketPrice(ticketVO.getTicketClass()));
        final Integer parkingPrice = getParkingCharges(ticketVO.getParkingVehicle());
        return Integer.valueOf(ticketVO.getNumberOfSeats()) * oneTicketPrice + parkingPrice;
    }

    private String getTicketPrice(String ticketClass) throws MovieWorldException {
        try {
            return messageSource.getMessage(ticketClass, null, Locale.US);
        } catch (NoSuchMessageException e) {
            throw new MovieWorldException("Ticket Class is not valid");
        }
    }

    private Integer getParkingCharges(String vehicle) {
        return parkingCharges.containsKey(vehicle) ? parkingCharges.get(vehicle) : NO_CHARGE;
    }
}
