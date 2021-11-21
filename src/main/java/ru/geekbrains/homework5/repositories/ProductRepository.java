package ru.geekbrains.homework5.repositories;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homework5.config.SessionFactoryConfig;
import ru.geekbrains.homework5.entities.Product;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@Repository
@Slf4j
@AllArgsConstructor
public class ProductRepository implements AbstractRepository<Product, Long> {

	private SessionFactoryConfig sessionFactoryConfig;

	@Override
	public List<Product> findAll() {
		try (Session session = sessionFactoryConfig.getSession()) {
			session.beginTransaction();
			List<Product> products = session.createQuery("select p from Product p").getResultList();
			log.info("Size {}", products.size());
			session.getTransaction().commit();
			return products;
		}
	}

	@Override
	public void deleteById(Long id) {
		try (Session session = sessionFactoryConfig.getSession()) {
			session.beginTransaction();
			session.createQuery("delete from Product product where product.id = :id")
					.setParameter("id", id)
					.executeUpdate();
			session.getTransaction().commit();
		}
	}

	@Override
	public Optional<Product> findById(Long id) {
		try (Session session = sessionFactoryConfig.getSession()) {
			session.beginTransaction();
			Product product = session.get(Product.class, id);
			session.getTransaction().commit();
			return Optional.ofNullable(product);
		}
	}

	@Override
	public void saveOrUpdate(Product product) {
		try (Session session = sessionFactoryConfig.getSession()) {
			session.beginTransaction();
			if (product.getId()==null)
				session.persist(product);
			else
				session.merge(product);
			session.getTransaction().commit();
		}
	}

	public void changePrice(Long id, Integer delta) {
		try (Session session = sessionFactoryConfig.getSession()) {
			session.beginTransaction();
			Product product = session.get(Product.class, id);
			product.setCost(product.getCost()+delta);
			session.getTransaction().commit();
		}
	}

}
