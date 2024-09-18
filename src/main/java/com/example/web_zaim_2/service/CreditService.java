package com.example.web_zaim_2.service;

import com.example.web_zaim_2.model.Credit;
import com.example.web_zaim_2.repository.CreditRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreditService {

    @Autowired
    CreditRepository creditRepository;

    public void calculatePaymentString() {
        List<Credit> credits = creditRepository.findAll();
        // Словарь для кодов платежей
        Map<Character, String> paymentCodes = Map.of(
                '1', "Вовремя",
                '0', "Не поступили",
                'A', "Просрочка 7 дней",
                '2', "Просрочка 29-39 дней",
                '3', "Просрочка свыше 39 дней",
                'X', "Нет данных"
        );

        // Список для хранения всех платежей
        Map<LocalDate, List<Character>> paymentLog = new HashMap<>();

        for (Credit credit : credits) {
            LocalDate paymentDate = credit.getFirstPaymentDate();

            for (char c : credit.getPaymentString().toCharArray()) {
                paymentLog.computeIfAbsent(paymentDate, k -> new ArrayList<>()).add(c);
                paymentDate = paymentDate.plusMonths(1); // Увеличиваем дату на один месяц
            }
        }

        // Создаем сквозную строку платежей
        StringBuilder result = new StringBuilder();
        // Для каждого месяца, создаем строку с учетом платежей
        for (LocalDate date : paymentLog.keySet()) {
            List<Character> monthPayments = paymentLog.get(date);
            char finalPaymentCode = 'X'; // Начальное значение для отсутствия данных

            // Логика выбора символа с самым большим уровнем просрочки
            for (char currentCode : monthPayments) {
                if (currentCode > finalPaymentCode) {
                    finalPaymentCode = currentCode;
                }
            }
            result.append(finalPaymentCode);
        }

        System.out.println("Сквозная строка платежей: " + result.toString());
    }

    public void exportToExcel(Map<LocalDate, List<Character>> paymentLog) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Payments");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Дата платежа");
        headerRow.createCell(1).setCellValue("Коды платежей");

        int rowNum = 1;

        for (Map.Entry<LocalDate, List<Character>> entry : paymentLog.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey().toString());
            row.createCell(1).setCellValue(entry.getValue().toString());
        }

        try (FileOutputStream fileOut = new FileOutputStream("Payments.xlsx")) {
            workbook.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}