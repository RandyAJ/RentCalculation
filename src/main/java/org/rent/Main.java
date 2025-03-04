package org.rent;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Приложение запущено");

        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            // Создаем репозиторий и сервис
            RecordRepository recordRepository = new RecordRepository(factory);
            RecordService recordService = new RecordService(recordRepository);

            // типа пришедшие параметры. намёк на рефакторинг в веб-приложуху с написанием реакта
            /*HashMap<String, Double> params = new HashMap<>();
            params.put("t1", 5399.42);
            params.put("t2", 3924.77);
            params.put("t3", 6799.34);
            params.put("hot", 350.677);
            params.put("cold", 505.539);
            // создаем новую запись
            Record record = recordService.save(params);

            System.out.println("Сохранено: " + record);*/

        } catch (Exception e) {
            System.out.println("Ошибка при создании записи: " + e.getMessage());
            e.printStackTrace();
        }
    }
}