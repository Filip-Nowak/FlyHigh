package org.example.flyhigh.entity.user;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "userProfile")
    private List<Ticket> tickets;
}
