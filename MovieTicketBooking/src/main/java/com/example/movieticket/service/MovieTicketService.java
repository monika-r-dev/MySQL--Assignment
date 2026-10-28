package com.example.movieticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.movieticket.exception.TicketNotFoundException;
import com.example.movieticket.model.MovieTicket;

@Service
public class MovieTicketService {

private List<MovieTicket> tickets = new ArrayList<>();
private int nextId = 1;

public List<MovieTicket> getAllTickets() {
    return tickets;
}

public MovieTicket getTicketById(int id) {
    return tickets.stream()
            .filter(ticket -> ticket.getTicketId() == id)
            .findFirst()
            .orElseThrow(() ->
                    new TicketNotFoundException("Ticket not found with ID: " + id));
}

public MovieTicket bookTicket(MovieTicket ticket) {
    ticket.setTicketId(nextId++);
    tickets.add(ticket);
    return ticket;
}

public MovieTicket updateTicket(int id, MovieTicket updatedTicket) {
    MovieTicket existingTicket = getTicketById(id);

    existingTicket.setMovieName(updatedTicket.getMovieName());
    existingTicket.setCustomerName(updatedTicket.getCustomerName());
    existingTicket.setSeatNumber(updatedTicket.getSeatNumber());
    existingTicket.setTicketPrice(updatedTicket.getTicketPrice());

    return existingTicket;
}

public void cancelTicket(int id) {
    MovieTicket ticket = getTicketById(id);
    tickets.remove(ticket);
}

}