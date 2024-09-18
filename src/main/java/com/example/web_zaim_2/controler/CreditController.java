package com.example.web_zaim_2.controler;


import com.example.web_zaim_2.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping("/calculate")
    public String calculatePaymentString() {
        creditService.calculatePaymentString();
        return "Расчет завершен. Проверьте консоль для результата.";
    }
}