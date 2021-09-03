package com.example.shortener.repository;

import com.example.shortener.model.Link;

import java.util.List;

public interface LinkRepository {
    void create(Link link);

    Link getLinkByShortName(String shortName);

    void update(Link link);

    void delete(Link link);

    List<Link> getListOfLinks(int page, int count);
}
