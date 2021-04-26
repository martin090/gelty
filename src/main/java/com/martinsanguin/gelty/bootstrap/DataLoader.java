package com.martinsanguin.gelty.bootstrap;

import com.martinsanguin.gelty.domain.Shift;
import com.martinsanguin.gelty.domain.Study;
import com.martinsanguin.gelty.repositories.ShiftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private ShiftRepository shiftRepository;

    public DataLoader(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
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

        log.info("####### End loading dummy data #######");
    }
}
