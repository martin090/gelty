package com.martinsanguin.gelty.bootstrap;

import com.martinsanguin.gelty.domain.Shift;
import com.martinsanguin.gelty.domain.Study;
import com.martinsanguin.gelty.domain.UserCredentials;
import com.martinsanguin.gelty.repositories.ShiftRepository;
import com.martinsanguin.gelty.repositories.UserCredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private ShiftRepository shiftRepository;
    private UserCredentialsRepository userCredentialsRepository;

    public DataLoader(ShiftRepository shiftRepository, UserCredentialsRepository userCredentialsRepository) {

        this.shiftRepository = shiftRepository;
        this.userCredentialsRepository = userCredentialsRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        log.info("####### Begin loading dummy data #######");

        Shift cardioStudy = new Study();
        cardioStudy.setId(1L);
        cardioStudy.setDate(Calendar.getInstance());

        Shift anotherStudy = new Study();
        anotherStudy.setId(2L);
        anotherStudy.setDate(Calendar.getInstance());

        this.shiftRepository.save(cardioStudy);
        this.shiftRepository.save(anotherStudy);

        UserCredentials admin = new UserCredentials();
        admin.setUsername("admin");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode("admin"));

        this.userCredentialsRepository.save(admin);

        log.info("####### End loading dummy data #######");
    }
}
