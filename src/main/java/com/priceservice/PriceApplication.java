package com.priceservice;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PriceApplication.class, args).close();
    }

    @Override
    public void run(String... args) {
        System.out.println("Waiting..." + "\n");
        new Scanner(System.in).nextLine();
    }
}

