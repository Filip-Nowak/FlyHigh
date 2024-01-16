package org.example.flyhigh.repository;

import org.example.flyhigh.entity.user.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
