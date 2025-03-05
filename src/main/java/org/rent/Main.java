package org.rent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info(" >>> Приложение запущено");

        System.setProperty("env.DB_URL", System.getenv("DB_URL"));  // костыли. файлик конфигурации в том виде в котором есть (не application.properties) во время сборки не хочет видеть переменные окружения
        System.setProperty("env.DB_USERNAME", System.getenv("DB_USERNAME"));
        System.setProperty("env.DB_PASSWORD", System.getenv("DB_PASSWORD"));

        dbConnection();

        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            // Создаем репозиторий, сервисы и вручную инъектируем сервисы через конструкторы.
            RecordRepository recordRepository = new RecordRepository(factory);
            RecordLogicService recordLogicService = new RecordLogicService(recordRepository);
            RecordService recordService = new RecordService(recordLogicService);

            // пример использования. намёк на рефакторинг в веб-приложуху с написанием реакта
            /* HashMap<String, Double> params = new HashMap<>();
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

    static void dbConnection() {
        try {
            Configuration configuration = new Configuration().configure(); // Загружаем hibernate.cfg.xml
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            logger.info(" ✅ Успешное подключение к БД через Hibernate!");
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            logger.error(" ❌ Ошибка подключения к БД: " + e.getMessage());
        }
    }
}