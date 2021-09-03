package com.example.shortener.repository.impl;

import com.example.shortener.model.User;
import com.example.shortener.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE username= :paramName").setParameter("paramName", username);
        List results = query.getResultList();
        return results.isEmpty() ? Optional.ofNullable(null) : Optional.of((User) results.get(0));
    }
}
