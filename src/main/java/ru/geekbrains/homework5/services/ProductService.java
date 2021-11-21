package ru.geekbrains.homework5.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework5.entities.Product;
import ru.geekbrains.homework5.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	public void add(Product product) {
		productRepository.saveOrUpdate(product);
	}

	public void changePrice(Long id, Integer delta) {
		productRepository.changePrice(id,delta);
	}

}
