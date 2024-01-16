package org.example.flyhigh.service;

import lombok.Data;
import org.example.flyhigh.entity.user.Ticket;
import org.example.flyhigh.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
@Data
public class TicketService {
    private final TicketRepository ticketRepository;
    public void saveTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }
}
