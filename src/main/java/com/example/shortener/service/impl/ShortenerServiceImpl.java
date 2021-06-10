package com.example.shortener.service.impl;

import com.example.shortener.model.Link;
import com.example.shortener.repository.ShortenerRepository;
import com.example.shortener.service.ShortenerService;
import com.example.shortener.utils.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShortenerServiceImpl implements ShortenerService {
    private ShortenerRepository shortenerRepository;

    @Autowired
    public void setShortenerRepository(ShortenerRepository shortenerRepository) {
        this.shortenerRepository = shortenerRepository;
    }

    @Override
    @Transactional
    public String generateShortName(String originalLink) {
        return new SequenceGenerator().getRandomSequence(8);
    }

    @Override
    @Transactional
    public Link getLinkByShortName(String shortName) {
        return shortenerRepository.getLinkByShortName(shortName);
    }

    @Override
    @Transactional
    public void createLink(Link link) {
        shortenerRepository.create(link);
    }

    @Override
    @Transactional
    public void updateLink(Link link) {
        shortenerRepository.update(link);
    }

    @Override
    @Transactional
    public List<Link> getListOfLinks(int page, int count) {
        return shortenerRepository.getListOfLinks(page,count);
    }
}
