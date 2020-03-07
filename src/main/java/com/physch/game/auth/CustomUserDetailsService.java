package com.physch.game.auth;

import com.physch.game.exceptions.NoSuchUserException;
import com.physch.game.model.User;
import com.physch.game.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User>  user =userRepository.findByEmail(email);
        if(user.isEmpty())
            throw new NoSuchUserException("No User is Registered with Email" + email);
        return new CustomUserDetails(user.get());
    }

}
