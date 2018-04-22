package com.bsdim.web.project.dao.api;

public interface IDao<K, E> {
    void create(E entity);
    E read(K key);
    void update(E entity);
    void delete(K key);
}