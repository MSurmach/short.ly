package com.example.shortener.repository.impl;

import com.example.shortener.model.Authority;
import com.example.shortener.repository.AuthorityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorityRepositoryImpl implements AuthorityRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Authority> getAuthorityByName(String authorityName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Authority WHERE authority= :paramName").setParameter("paramName", authorityName);
        List results = query.getResultList();
        Authority loadedAuthority = (Authority) results.get(0);
        return Optional.ofNullable(loadedAuthority);
    }
}
