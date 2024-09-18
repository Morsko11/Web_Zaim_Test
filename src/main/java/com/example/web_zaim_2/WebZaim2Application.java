package com.example.web_zaim_2;

import com.example.web_zaim_2.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebZaim2Application {
@Autowired
private static CreditService creditService;
    public static void main(String[] args) {
        SpringApplication.run(WebZaim2Application.class, args);

    }

}
