package com.example.shortener.service.impl;

import com.example.shortener.model.Link;
import com.example.shortener.repository.LinkRepository;
import com.example.shortener.service.LinkService;
import com.example.shortener.utils.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    private LinkRepository linkRepository;

    @Autowired
    public void setShortenerRepository(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    @Transactional
    public String generateShortName(String originalLink) {
        return new SequenceGenerator().getRandomSequence(8);
    }

    @Override
    @Transactional
    public Link getLinkByShortName(String shortName) {
        return linkRepository.getLinkByShortName(shortName);
    }

    @Override
    @Transactional
    public void create(Link link) {
        linkRepository.create(link);
    }

    @Override
    @Transactional
    public void update(Link link) {
        linkRepository.update(link);
    }

    @Override
    @Transactional
    public void delete(Link link) {
        linkRepository.delete(link);
    }

    @Override
    @Transactional
    public List<Link> getListOfLinks(int page, int count) {
        return linkRepository.getListOfLinks(page, count);
    }
}
