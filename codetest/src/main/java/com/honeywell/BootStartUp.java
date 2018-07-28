package com.honeywell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Hackathon on 7/28/2018.
 */

@SpringBootApplication
@ComponentScan("com.honeywell")
public class BootStartUp {
    public static void main(String args[]){
        SpringApplication.run(BootStartUp.class,args);
    }
}
