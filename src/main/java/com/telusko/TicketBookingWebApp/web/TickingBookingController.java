package com.telusko.TicketBookingWebApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.telusko.TicketBookingWebApp.model.Passenger;
import com.telusko.TicketBookingWebApp.model.Ticket;
import com.telusko.TicketBookingWebApp.service.ITicketBookingService;

@Controller
public class TickingBookingController
{
	@Autowired
	private ITicketBookingService service;
	
	@PostMapping("/book-ticket")
	public String bookTicket(@ModelAttribute Passenger passenger, Model model)
	{
		Integer ticketNumber=service.registerPassenger(passenger);
		model.addAttribute("ticketNumber", ticketNumber);
		return "index";
	}
	
	@GetMapping("/form")
	public String getTicketForm(@ModelAttribute Passenger passenger,Model model)
	{
		model.addAttribute("passenger", new Passenger());
		return "index";
	}
	@GetMapping("/ticket")
	public String getForm(Model model)
	{
		
		return "ticket-form";
	}
	
	@GetMapping("/get-ticket")
	public String getFullTicket(@RequestParam("ticketNumber")Integer ticketNumber, Model model)
	{
		Ticket ticket = service.getTicket(ticketNumber);
		model.addAttribute("ticket", ticket);
		return "ticket-info";
	}
}
