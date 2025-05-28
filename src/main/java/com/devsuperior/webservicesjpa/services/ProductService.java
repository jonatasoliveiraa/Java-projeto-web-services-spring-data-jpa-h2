package com.devsuperior.webservicesjpa.services;

import com.devsuperior.webservicesjpa.entities.Product;
import com.devsuperior.webservicesjpa.repositories.ProductRepository;
import com.devsuperior.webservicesjpa.services.exceptions.DatabaseException;
import com.devsuperior.webservicesjpa.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Product insert(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Long id, Product product) {
        try {
            Product entity = productRepository.getReferenceById(id);

            entity.setName(product.getName() != null ? product.getName() : entity.getName());
            entity.setDescription(product.getDescription() != null ? product.getDescription() : entity.getDescription());
            entity.setPrice(product.getPrice() != null ? product.getPrice() : entity.getPrice());
            entity.setImgUrl(product.getImgUrl() != null ? product.getImgUrl() : entity.getImgUrl());

            return entity;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
