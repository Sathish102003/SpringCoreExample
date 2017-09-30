package com.ca.spring.movie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ca.spring.movie.exception.MovieWorldException;
import com.ca.spring.movie.vo.TicketVO;

import static com.ca.spring.movie.constants.SQLConstants.INSERT_RESERVATION_DETAILS;
import static com.ca.spring.movie.constants.SQLConstants.SELECT_REMAINING_TICKETS;
import static com.ca.spring.movie.constants.SQLConstants.UPDATE_TICKET_DETAILS;

@Component
public class MovieWorldTicketDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveReservationDetails(final TicketVO ticketVO) throws MovieWorldException {
        jdbcTemplate.update(INSERT_RESERVATION_DETAILS, ticketVO.getMovieCode(), ticketVO.getTicketClass(),
                ticketVO.getNumberOfSeats(),
                ticketVO.getDate(), ticketVO.getTime());
    }

    public void updateAvailableSeats(final TicketVO ticketVO) throws MovieWorldException {
        Integer currentRemaining = jdbcTemplate
                .queryForObject(SELECT_REMAINING_TICKETS, new String[] { ticketVO.getMovieCode() }, Integer.class);
        Integer newRemaining = currentRemaining - Integer.valueOf(ticketVO.getNumberOfSeats());
        if (newRemaining < 0) {
            throw new MovieWorldException("No Ticket is Available");
        }
        jdbcTemplate.update(UPDATE_TICKET_DETAILS, newRemaining, ticketVO.getMovieCode());
    }

}
