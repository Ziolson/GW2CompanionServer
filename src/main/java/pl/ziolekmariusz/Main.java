package pl.ziolekmariusz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class Main {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);

    }

}
