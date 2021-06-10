package com.example.shortener.repository.impl;

import com.example.shortener.model.Link;
import com.example.shortener.repository.ShortenerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShortenerRepositoryImpl implements ShortenerRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Link link) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(link);
    }

    @Override
    public Link getLinkByShortName(String shortName) {
        Session session = sessionFactory.getCurrentSession();
        Link link = session.get(Link.class, shortName);
        return link;
    }

    @Override
    public void update(Link link) {
        Session session = sessionFactory.getCurrentSession();
        session.update(link);
    }

    @Override
    public List<Link> getListOfLinks(int page, int count) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Link L order by L.count DESC").setFirstResult(count * (page - 1)).setMaxResults(count).list();
    }
}
