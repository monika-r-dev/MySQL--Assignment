package com.example.movieticket.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class MovieTicket {

private int ticketId;

@NotBlank(message = "Movie name is required")
private String movieName;

@NotBlank(message = "Customer name cannot be empty")
private String customerName;

private String seatNumber;

@Positive(message = "Ticket price must be positive")
private double ticketPrice;

public MovieTicket() {
}

public MovieTicket(int ticketId, String movieName, String customerName,
                   String seatNumber, double ticketPrice) {
    this.ticketId = ticketId;
    this.movieName = movieName;
    this.customerName = customerName;
    this.seatNumber = seatNumber;
    this.ticketPrice = ticketPrice;
}


public int getTicketId() {
    return ticketId;
}

public void setTicketId(int ticketId) {
    this.ticketId = ticketId;
}

public String getMovieName() {
    return movieName;
}

public void setMovieName(String movieName) {
    this.movieName = movieName;
}

public String getCustomerName() {
    return customerName;
}

public void setCustomerName(String customerName) {
    this.customerName = customerName;
}

public String getSeatNumber() {
    return seatNumber;
}

public void setSeatNumber(String seatNumber) {
    this.seatNumber = seatNumber;
}

public double getTicketPrice() {
    return ticketPrice;
}

public void setTicketPrice(double ticketPrice) {
    this.ticketPrice = ticketPrice;
}

}