package com.task.interview.services;

import com.task.interview.repositories.OrdersRepository;
import com.task.interview.repositories.ProductsRepository;
import com.task.interview.repositories.entities.Customer;
import com.task.interview.repositories.entities.Order;
import com.task.interview.repositories.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository orderRepository;
    private final ProductsRepository productsRepository;

    private final EntityManager entityManager;

    public List<Order> getOrders(long customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    public Order createOrder(long productId, long customerId) {
        Customer customer = entityManager.getReference(Customer.class, customerId);
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(String.format("Product with id %s not found", productId)));


        BigDecimal price = product.getPrice();

        Order toSave = Order.builder()
                .product(product)
                .customer(customer)
                .price(price)
                .build();
        return orderRepository.save(toSave);
    }
}
