package com.example.shortener.repository;

public interface CrudRepository<T> {

    void save(T entity);

    void delete(T entity);

    void update(T entity);
}
