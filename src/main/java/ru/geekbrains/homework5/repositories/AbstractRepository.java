package ru.geekbrains.homework5.repositories;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T, Long> {
	List<T> findAll();
	Optional<T> findById(Long id);
	void saveOrUpdate(T t);
	void deleteById(Long id);

}
