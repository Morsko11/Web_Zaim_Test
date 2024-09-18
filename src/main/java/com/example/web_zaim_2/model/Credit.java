package com.example.web_zaim_2.model;



import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentString;

    private LocalDate firstPaymentDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentString() {
        return paymentString;
    }

    public void setPaymentString(String paymentString) {
        this.paymentString = paymentString;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(LocalDate firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }
}