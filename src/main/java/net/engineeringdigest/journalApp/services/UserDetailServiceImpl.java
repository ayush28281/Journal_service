package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserEntryRepository UserEntryS;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users newUser = UserEntryS.findByUsername(username);
        if (newUser != null) {

            return User.builder().
                    username(newUser.getUsername()).
                    password(newUser.getPassword()).
                    roles(newUser.getRoles().toArray(new String[0])).
                    build();
        }
        throw new UsernameNotFoundException("Username not found" + username);
    }
}
