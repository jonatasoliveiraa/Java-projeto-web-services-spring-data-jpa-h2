package com.devsuperior.webservicesjpa.repositories;

import com.devsuperior.webservicesjpa.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
