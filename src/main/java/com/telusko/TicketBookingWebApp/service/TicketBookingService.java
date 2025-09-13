package com.telusko.TicketBookingWebApp.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.telusko.TicketBookingWebApp.model.Passenger;
import com.telusko.TicketBookingWebApp.model.Ticket;

@Service
public class TicketBookingService implements ITicketBookingService
{
	
	private final String  BOOKING_URL="http://localhost:9001/TicketBookingAPI/api/book-ticket/get-ticketnumber";
	private final String GET_URL="http://localhost:9001/TicketBookingAPI/api/book-ticket/get-full-ticket/{ticketNumber}";

	@Override
	public Integer registerPassenger(Passenger passenger)
	{
//		RestTemplate restTemplate=new RestTemplate();
//		ResponseEntity<Ticket> response = restTemplate.postForEntity(BOOKING_URL, passenger, Ticket.class);
//		Ticket ticket = response.getBody();
//		Integer ticketNumber=ticket.getTicketNumber();
		
		//using Web Client In place of Rest Template
		
		WebClient webClient = WebClient.create();
		Ticket ticket=webClient
		.post()
		.uri(BOOKING_URL)
		.bodyValue(passenger)
		.retrieve()
		.bodyToMono(Ticket.class)
		.block();
		Integer ticketNumber=ticket.getTicketNumber();
		return ticketNumber;
	}

	@Override
	public Ticket getTicket(Integer ticketNumber)
	{
//		RestTemplate restTemplate=new RestTemplate();
//		ResponseEntity<Ticket> response = restTemplate.getForEntity(GET_URL, Ticket.class, ticketNumber);
//		Ticket ticket = response.getBody();
		
		WebClient webClient = WebClient.create();
		Ticket ticket=webClient
				.get()
				.uri(GET_URL,  ticketNumber)
				.retrieve()
				.bodyToMono(Ticket.class)
				.block();
				//.subscribe()
		return ticket;
	}
	

}
