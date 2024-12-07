package com.mockproject.givetoget;

import com.mockproject.givetoget.config.dataseed.SeedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GiveToGetApplication implements CommandLineRunner {
    @Autowired
    private SeedData seedData;

    public static void main(String[] args) {
        SpringApplication.run(GiveToGetApplication.class, args);
    }


    @Override
    public void run(String... args) throws IOException {
        seedData.addData();
    }
}
