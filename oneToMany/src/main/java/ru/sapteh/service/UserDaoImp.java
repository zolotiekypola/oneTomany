package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.User;

import java.util.List;

public class UserDaoImp implements DAO<User, Integer> {
    private final SessionFactory factory;

    public UserDaoImp(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public User read(Integer id) {
        try (Session session = factory.openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    @Override
    public List<User> findByAll() {
        try (Session session = factory.openSession()) {
            Query<User> result = session.createQuery("FROM User");
            return result.list();
        }
    }

    @Override
    public void create(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}