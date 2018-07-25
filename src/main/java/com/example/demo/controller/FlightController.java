package com.example.demo.controller;

import com.example.demo.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class FlightController {

    @GetMapping("/flights/flight")
    public Flight getFlight() {
        Flight.Person person = new Flight.Person();
        person.setFirstName("Some name");
        person.setLastName("Some other name");

        Flight.Ticket ticket = new Flight.Ticket();
        ticket.setPassenger(person);
        ticket.setPrice(200);

        Flight flight = new Flight();
        flight.setTickets(asList(ticket));
        flight.setDeparts(new Date(1532553773000L));

        return flight;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        Flight.Person person1 = new Flight.Person();
        person1.setFirstName("Some name");
        person1.setLastName("Some other name");

        Flight.Ticket ticket1 = new Flight.Ticket();
        ticket1.setPassenger(person1);
        ticket1.setPrice(200);

        Flight flight1 = new Flight();
        flight1.setTickets(asList(ticket1));
        flight1.setDeparts(new Date(1532553773000L));

        Flight.Person person2 = new Flight.Person();
        person2.setFirstName("Some other name");
        person2.setLastName(null);

        Flight.Ticket ticket2 = new Flight.Ticket();
        ticket2.setPassenger(person2);
        ticket2.setPrice(400);

        Flight flight2 = new Flight();
        flight2.setTickets(asList(ticket2));
        flight2.setDeparts(new Date(1532553773000L));

        return asList(flight1, flight2);

    }
}
