package com.martinsanguin.gelty.services;

import com.martinsanguin.gelty.domain.UserCredentials;
import com.martinsanguin.gelty.repositories.UserCredentialsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {

    private final UserCredentialsRepository userCredentialsRepository;

    public UserCredentialsServiceImpl(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        Optional<UserCredentials> user = this.userCredentialsRepository.findById(username);
        if(user.isPresent())
            return user.get();
        else
            throw new UsernameNotFoundException("Username not found.");
    }
}
