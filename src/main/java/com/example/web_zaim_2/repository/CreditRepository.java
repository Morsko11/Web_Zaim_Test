package com.example.web_zaim_2.repository;


import com.example.web_zaim_2.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findAll();
}
