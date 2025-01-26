package org.rent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Создаем SessionFactory, используя файл hibernate.cfg.xml
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();
        Record rec = new Record(28.19, 24.06, 43.4, 1.709, 4.101);

        session.save(rec);

        session.getTransaction().commit();

        System.out.println("Сохранено: " + rec);

        // Закрываем сессию и фабрику
        session.close();
        factory.close();
    }
}