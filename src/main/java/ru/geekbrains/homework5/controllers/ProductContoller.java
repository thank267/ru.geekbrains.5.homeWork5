package ru.geekbrains.homework5.controllers;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework5.entities.Product;
import ru.geekbrains.homework5.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductContoller {

	private final ProductService service;

	@GetMapping
	public List<Product> index(Model model) {
		return service.findAll();
	}

	@GetMapping("/delete/{id}")
	public void delete(@PathVariable("id") long id) {
		 service.deleteById(id);
	}

	@GetMapping("/change_price/{id}")
	public void change(@PathVariable("id") long id, @RequestParam Integer delta) {
		service.changePrice(id, delta);
	}



}
