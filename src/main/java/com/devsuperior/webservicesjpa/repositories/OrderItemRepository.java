package com.devsuperior.webservicesjpa.repositories;

import com.devsuperior.webservicesjpa.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
