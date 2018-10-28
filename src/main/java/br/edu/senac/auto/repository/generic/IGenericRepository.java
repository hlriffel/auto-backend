package br.edu.senac.auto.repository.generic;

import java.util.List;

public interface IGenericRepository<T> {

    void setClazz(Class<T> clazz);

    T findOne(Long id);

    List<T> findAll();

    void add(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(Long id);
}
