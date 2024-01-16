package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.repository.UserProfileRepository;
import org.example.flyhigh.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final TicketService ticketService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    public void saveUser(UserDetails userDetails){
        userRepository.save((org.example.flyhigh.entity.user.User) userDetails);
    }
}
