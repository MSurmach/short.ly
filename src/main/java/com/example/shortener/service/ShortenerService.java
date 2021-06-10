package com.example.shortener.service;

import com.example.shortener.model.Link;

import java.util.List;

public interface ShortenerService {

    String generateShortName(String originalLink);

    Link getLinkByShortName(String shortName);

    void createLink(Link link);

    void updateLink(Link link);

    List<Link> getListOfLinks(int page, int count);
}
