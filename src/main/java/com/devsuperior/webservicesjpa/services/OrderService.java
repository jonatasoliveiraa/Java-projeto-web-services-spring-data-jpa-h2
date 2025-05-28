package com.devsuperior.webservicesjpa.services;

import com.devsuperior.webservicesjpa.entities.Order;
import com.devsuperior.webservicesjpa.repositories.OrderRepository;
import com.devsuperior.webservicesjpa.services.exceptions.DatabaseException;
import com.devsuperior.webservicesjpa.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Order insert(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            orderRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Order update(Long id, Order order) {
        try {
            Order entity = orderRepository.getReferenceById(id);

            entity.setPayment(order.getPayment() != null ? order.getPayment() : entity.getPayment());
            entity.setClient(order.getClient() != null ? order.getClient() : entity.getClient());
            entity.setOrderStatus(order.getOrderStatus() != null ? order.getOrderStatus() : entity.getOrderStatus());
            entity.setMoment(order.getMoment() != null ? order.getMoment() : entity.getMoment());

            return entity;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
