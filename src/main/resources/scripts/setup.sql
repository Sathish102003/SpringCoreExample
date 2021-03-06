DROP TABLE if EXISTS RESERVATION_DETAILS;
DROP TABLE if EXISTS TICKET_AVAILABILITY;

CREATE TABLE IF NOT EXISTS RESERVATION_DETAILS (MOVIE_CODE VARCHAR(50), TICKET_CLASS VARCHAR(10), NUMBER_OF_SEATS int(10), TICKET_DATE Date, TICKET_TIME VARCHAR(10));
CREATE TABLE IF NOT EXISTS TICKET_AVAILABILITY (MOVIE_CODE VARCHAR(50), REMAINING_TICKETS int(10));

Insert into TICKET_AVAILABILITY (MOVIE_CODE, REMAINING_TICKETS) values ('Movie1', 15);
Insert into TICKET_AVAILABILITY (MOVIE_CODE, REMAINING_TICKETS) values ('Movie2', 25);
Insert into TICKET_AVAILABILITY (MOVIE_CODE, REMAINING_TICKETS) values ('Movie3', 5);
Insert into TICKET_AVAILABILITY (MOVIE_CODE, REMAINING_TICKETS) values ('Movie4', 100);
Insert into TICKET_AVAILABILITY (MOVIE_CODE, REMAINING_TICKETS) values ('Movie5', 50);
