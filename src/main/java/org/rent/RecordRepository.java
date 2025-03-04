package org.rent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RecordRepository {
    private final SessionFactory sessionFactory;

    public RecordRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Запрос на получение записи по айди
    public Record findById(Long id){
        try (Session session = sessionFactory.openSession()) {
            return session.get(Record.class, id);
        }
    }

    // Запрос на получение самой последней записи по дате
    public Record findLatestRecordByDate() {
        try (Session session = sessionFactory.openSession()) {
            Query<Record> query = session.createQuery("FROM Record ORDER BY dateRecord DESC", Record.class);
            query.setMaxResults(1); // Получаем только одну запись, самую свежую
            return query.uniqueResult();
        }
    }

    public void save(Record record) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(record);
            session.getTransaction().commit();
        }
    }
}
