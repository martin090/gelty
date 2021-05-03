package com.martinsanguin.gelty.repositories;

import com.martinsanguin.gelty.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials,String> {
}
