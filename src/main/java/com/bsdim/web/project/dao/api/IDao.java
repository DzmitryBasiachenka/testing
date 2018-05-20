package com.bsdim.web.project.dao.api;

/**
 * The IDao for work with four base functions.
 * <p>
 * Date: 2017-11-27
 *
 * @author Dzmitry Basiachenka
 *
 * @param <K>
 * @param <E>
 */
public interface IDao<K, E> {
    /**
     * Creates.
     *
     * @param entity the entity.
     * @return the key.
     */
    K create(E entity);

    /**
     * Reads.
     *
     * @param key the key.
     * @return the entity.
     */
    E read(K key);

    /**
     * Updates.
     *
     * @param entity the entity.
     */
    void update(E entity);

    /**
     * Deletes.
     *
     * @param key the key.
     */
    void delete(K key);
}
