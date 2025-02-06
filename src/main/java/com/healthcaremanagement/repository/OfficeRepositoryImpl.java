package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OfficeRepositoryImpl {

    private final SessionFactory sessionFactory;

    public OfficeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createOffice(Office office) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(office); // or session.persist(office) if preferred
            transaction.commit();
        }
    }


    public Office getOfficeById(int officeId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Office.class, officeId);
        }
    }


    public void updateOffice(Office office) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(office);
            transaction.commit();
        }
    }

    public void deleteOffice(int officeId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Office office = session.get(Office.class, officeId);
            if (office != null) {
                session.remove(office);
            } transaction.commit();
        }
    }


    public List<Office> getAllOffices() {
        try (Session session = sessionFactory.openSession()) {
            Query<Office> query = session.createQuery("FROM Office", Office.class);
            return query.list();
        }
    }
}

