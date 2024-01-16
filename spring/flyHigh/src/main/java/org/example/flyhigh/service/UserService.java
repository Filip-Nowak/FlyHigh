package org.example.flyhigh.service;

import lombok.AllArgsConstructor;
import org.example.flyhigh.entity.user.Role;
import org.example.flyhigh.entity.user.User;
import org.example.flyhigh.repository.RoleRepository;
import org.example.flyhigh.repository.UserProfileRepository;
import org.example.flyhigh.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final TicketService ticketService;
    private final RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    public void saveUser(User user){
        Optional<Role> optionalRole = roleRepository.findByName("USER");
        if(optionalRole.isEmpty()){
            throw new RuntimeException("Role not found");
        }
        Role role = optionalRole.get();
        System.out.println(role.getName());
        User savedUser = userRepository.save(user);
        savedUser.addRole(role);
        userRepository.save(savedUser);
    }
}
