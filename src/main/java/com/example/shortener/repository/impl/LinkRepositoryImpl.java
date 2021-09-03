package com.example.shortener.repository.impl;

import com.example.shortener.model.Link;
import com.example.shortener.repository.LinkRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

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
    public Link getLinkByShortName(String shortname) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Link WHERE shortname= :paramName").setParameter("paramName", shortname);
        List results = query.getResultList();
        return (Link) results.get(0);
    }

    @Override
    public void update(Link link) {
        Session session = sessionFactory.getCurrentSession();
        session.update(link);
    }

    @Override
    public void delete(Link link) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(link);
    }

    @Override
    public List<Link> getListOfLinks(int page, int count) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Link L order by L.count DESC").setFirstResult(count * (page - 1)).setMaxResults(count).list();
    }
}
