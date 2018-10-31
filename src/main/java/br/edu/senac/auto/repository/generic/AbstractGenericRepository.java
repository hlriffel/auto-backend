package br.edu.senac.auto.repository.generic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractGenericRepository<T> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Transactional
    public T findOne(Long id) {
        return entityManager.find(clazz, id);
    }

    @Transactional
    public List<T> findAll() {
        return entityManager.createQuery(" from " + clazz.getSimpleName()).getResultList();
    }

    @Transactional
    public void add(T entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        T entity = findOne(id);
        delete(entity);
    }
}
