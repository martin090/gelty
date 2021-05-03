package com.martinsanguin.gelty;

import com.martinsanguin.gelty.configuration.SecurityConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ SecurityConfigurer.class })
public class GeltyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeltyApplication.class, args);
    }

}
