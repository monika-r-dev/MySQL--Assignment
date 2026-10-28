package com.example.movieticket.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieticket.model.MovieTicket;
import com.example.movieticket.service.MovieTicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tickets")
public class MovieTicketController {

private final MovieTicketService ticketService;

public MovieTicketController(MovieTicketService ticketService) {
    this.ticketService = ticketService;
}

// Get all tickets
@GetMapping
public List<MovieTicket> getAllTickets() {
    return ticketService.getAllTickets();
}

// Get ticket by ID
@GetMapping("/{id}")
public MovieTicket getTicketById(@PathVariable int id) {
    return ticketService.getTicketById(id);
}

// Book a new ticket
@PostMapping
public ResponseEntity<MovieTicket> bookTicket(
        @Valid @RequestBody MovieTicket ticket) {

    MovieTicket bookedTicket = ticketService.bookTicket(ticket);

    return new ResponseEntity<>(bookedTicket, HttpStatus.CREATED);
}

// Update ticket details
@PutMapping("/{id}")
public MovieTicket updateTicket(
        @PathVariable int id,
        @Valid @RequestBody MovieTicket updatedTicket) {

    return ticketService.updateTicket(id, updatedTicket);
}

// Cancel ticket
@DeleteMapping("/{id}")
public ResponseEntity<String> cancelTicket(@PathVariable int id) {

    ticketService.cancelTicket(id);

    return ResponseEntity.ok("Ticket cancelled successfully");
}

}