package com.example.shortener.service;

import com.example.shortener.model.Link;

import java.util.List;

/**
 * Service which manages all operations on the user's links.
 */
public interface LinkService {

    String generateShortName(String originalLink);

    Link getLinkByShortName(String shortName);

    void create(Link link);

    void update(Link link);

    void delete(Link link);

    List<Link> getListOfLinks(int page, int count);
}
