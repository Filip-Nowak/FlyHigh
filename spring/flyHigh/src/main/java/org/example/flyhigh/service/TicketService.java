package org.example.flyhigh.service;

import lombok.Data;
import org.example.flyhigh.entity.Flight;
import org.example.flyhigh.entity.Seat;
import org.example.flyhigh.entity.user.Ticket;
import org.example.flyhigh.entity.user.UserProfile;
import org.example.flyhigh.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Data
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    public void saveTicket(List<Integer> seatNumbers, UserProfile user, Flight flight){
        List<Seat> seats = seatService.getSeatsByNumbers(seatNumbers,flight);
        if(seats.size()!=seatNumbers.size()){
            throw new RuntimeException("Seats not found");
        }
        Ticket ticket = Ticket.builder()
                .user(user)
                .price(0)
                .seats(seats)
                .build();

        double price = 0;
        for (Seat seat : seats) {
            price+=seat.getPrice();
            seat.setTicket(ticket);
        }
        ticket.setPrice(price);
        ticketRepository.save(ticket);
    }
}
