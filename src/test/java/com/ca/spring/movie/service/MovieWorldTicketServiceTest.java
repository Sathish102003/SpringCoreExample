package com.ca.spring.movie.service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ca.spring.movie.config.MovieWorldTicketConfig;
import com.ca.spring.movie.exception.MovieWorldException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MovieWorldTicketConfig.class })
public class MovieWorldTicketServiceTest {

    @Autowired
    private MovieWorldTicketService movieWorldTicketService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void reserveSeats() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        final int amount = movieWorldTicketService
                .reserveSeats("Movie1", "BALCONY", "10", dateFormat.format(new Date()), "10.30", "Car");
        assertThat(amount, is(3012));
    }

    @Test
    public void reserveSeatsNoSeatAvailableMessageException() throws Exception {
        thrown.expect(MovieWorldException.class);
        thrown.expectMessage("No Ticket is Available");
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        movieWorldTicketService
                .reserveSeats("Movie3", "ECONOMY", "10", dateFormat.format(new Date()), "10.30", "None");
    }

    @Test
    public void reserveSeatsTicketClassNotValidMessageException() throws Exception {
        thrown.expect(MovieWorldException.class);
        thrown.expectMessage("Ticket Class is not valid");
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        movieWorldTicketService
                .reserveSeats("Movie3", "TEST", "2", dateFormat.format(new Date()), "10.30", "None");
    }

    @Test
    public void reserveSeatsDateFormatMessageException() throws Exception {
        thrown.expect(MovieWorldException.class);
        thrown.expectMessage("Date Format is not valid");
        DateFormat dateFormat = new SimpleDateFormat("dd-yyyy");
        movieWorldTicketService
                .reserveSeats("Movie3", "BALCONY", "10", dateFormat.format(new Date()), "10.30", "None");
    }

}
