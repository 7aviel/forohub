package org.oracleone.forohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class ForohubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForohubApplication.class, args);
    }

}
