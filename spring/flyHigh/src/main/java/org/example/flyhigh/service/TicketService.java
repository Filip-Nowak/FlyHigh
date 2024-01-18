package org.example.flyhigh.service;

import lombok.Data;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Seat;
import org.example.flyhigh.entity.user.Ticket;
import org.example.flyhigh.entity.user.UserProfile;
import org.example.flyhigh.enums.SeatClass;
import org.example.flyhigh.repository.SeatRepository;
import org.example.flyhigh.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Data
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    private final SeatRepository seatRepository;
    public Ticket saveTicket(List<Integer> seatNumbers, UserProfile user, Flight flight){
        List<Seat> seats = seatService.getSeatsByNumbers(seatNumbers,flight);
        if(seats.size()!=seatNumbers.size()){
            throw new RuntimeException("Seats not found");
        }
        for(Seat seat:seats){
            if(seat.getTicket()!=null){
                throw new RuntimeException("Seat is already taken");
            }
        }
        Ticket ticket = Ticket.builder()
                .user(user)
                .price(0)
                .build();

        double price = 0;
        Ticket savedTicket=ticketRepository.save(ticket);
        for (Seat seat : seats) {
            if(seat.getSeatClass()== SeatClass.ECONOMY)
                price+=flight.getEconomyPrice();
            else if(seat.getSeatClass()== SeatClass.BUSINESS)
                price+=flight.getBusinessPrice();
            else if(seat.getSeatClass()== SeatClass.FIRST)
                price+=flight.getFirstPrice();
            seat.setTicket(savedTicket);
            seatRepository.save(seat);
        }
        ticket.setSeats(seats);
        ticket.setPrice(price);
        return ticketRepository.save(ticket);
    }
}
