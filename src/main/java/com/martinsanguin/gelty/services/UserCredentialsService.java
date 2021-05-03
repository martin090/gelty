package com.martinsanguin.gelty.services;

//import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserCredentialsService {
    UserDetails getUserByUsername(String username);
}
