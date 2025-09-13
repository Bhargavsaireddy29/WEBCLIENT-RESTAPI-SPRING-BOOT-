package com.telusko.TicketBookingWebApp.service;

import com.telusko.TicketBookingWebApp.model.Passenger;
import com.telusko.TicketBookingWebApp.model.Ticket;

public interface ITicketBookingService 
{
  Integer registerPassenger(Passenger passenger);
  Ticket getTicket(Integer ticketNumber);
}
