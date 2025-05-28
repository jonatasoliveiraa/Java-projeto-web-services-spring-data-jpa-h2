package com.devsuperior.webservicesjpa.repositories;

import com.devsuperior.webservicesjpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
