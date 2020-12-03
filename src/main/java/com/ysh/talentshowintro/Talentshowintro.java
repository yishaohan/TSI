package com.ysh.talentshowintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Talentshowintro {

    public static void main(String[] args) {
        SpringApplication.run(Talentshowintro.class, args);
    }

}
