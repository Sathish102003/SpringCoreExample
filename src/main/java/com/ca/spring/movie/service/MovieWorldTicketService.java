package com.ca.spring.movie.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import com.ca.spring.movie.bo.MovieWorldTicketBO;
import com.ca.spring.movie.exception.MovieWorldException;
import com.ca.spring.movie.vo.TicketVO;

@Component
public class MovieWorldTicketService {

    @Autowired
    private MovieWorldTicketBO movieWorldTicketBO;

    public int reserveSeats(final String movieCode, final String ticketClass, final String numberOfSeats,
                            final String date, final String time, final String parkingVehicle)
            throws MovieWorldException {
        return movieWorldTicketBO
                .reserveSeats(getTicketVO(movieCode, ticketClass, numberOfSeats, date, time, parkingVehicle));
    }

    private TicketVO getTicketVO(final String movieCode, final String ticketClass, final String numberOfSeats,
                                 final String date, final String time, final String parkingVehicle)
            throws MovieWorldException {
        final TicketVO ticketVO = new TicketVO();
        ticketVO.setDate(new Date());
        ticketVO.setMovieCode(movieCode);
        ticketVO.setNumberOfSeats(numberOfSeats);
        ticketVO.setTicketClass(ticketClass);
        ticketVO.setTime(time);
        ticketVO.setParkingVehicle(parkingVehicle);
        try {
            DateFormatter dtform = new DateFormatter();
            dtform.setPattern("MM-dd-yyyy");
            ticketVO.setDate(dtform.parse(date, Locale.FRANCE));
        } catch (ParseException e) {
            throw new MovieWorldException("Date Format is not valid");
        }
        return ticketVO;
    }

}
