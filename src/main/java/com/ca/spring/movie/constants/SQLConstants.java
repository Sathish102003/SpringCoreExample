/**
 *
 */
package com.ca.spring.movie.constants;

public class SQLConstants {

    public static final String UPDATE_TICKET_DETAILS = "update TICKET_AVAILABILITY set REMAINING_TICKETS = ? WHERE MOVIE_CODE = ?";
    public static final String INSERT_RESERVATION_DETAILS =
            "insert into RESERVATION_DETAILS  (MOVIE_CODE, TICKET_CLASS, NUMBER_OF_SEATS, TICKET_DATE, TICKET_TIME) values (?, ?, ?, ?, ?)";
    public static final String SELECT_REMAINING_TICKETS =
            "SELECT REMAINING_TICKETS FROM TICKET_AVAILABILITY WHERE MOVIE_CODE = ?";

}
