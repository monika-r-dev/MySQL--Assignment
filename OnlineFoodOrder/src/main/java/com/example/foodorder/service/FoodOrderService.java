package com.example.foodorder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.foodorder.model.FoodOrder;
import com.example.foodorder.exception.OrderNotFoundException;

@Service
public class FoodOrderService {

    private final List<FoodOrder> orders = new ArrayList<>();

    private final AtomicInteger idGenerator = new AtomicInteger(1);

    // Get all orders
    public List<FoodOrder> getAllOrders() {
        return new ArrayList<>(orders);
    }

    // Get order by ID
    public FoodOrder getOrderById(int id) {

        return orders.stream()
                .filter(order -> order.getOrderId() == id)
                .findFirst()
                .orElseThrow(() ->
                    new OrderNotFoundException(
                        "Order with ID " + id + " not found"));
    }

    // Add order
    public FoodOrder addOrder(FoodOrder order) {

        order.setOrderId(idGenerator.getAndIncrement());

        orders.add(order);

        return order;
    }

    // Update complete order
    public FoodOrder updateOrder(int id, FoodOrder updatedOrder) {

        FoodOrder existingOrder = getOrderById(id);

        existingOrder.setCustomerName(
                updatedOrder.getCustomerName());

        existingOrder.setFoodItem(
                updatedOrder.getFoodItem());

        existingOrder.setQuantity(
                updatedOrder.getQuantity());

        existingOrder.setPrice(
                updatedOrder.getPrice());

        return existingOrder;
    }

    // Delete order
    public void deleteOrder(int id) {

        FoodOrder order = getOrderById(id);

        orders.remove(order);
    }

    // Update quantity only
    public FoodOrder updateQuantity(int id, int quantity) {

        FoodOrder order = getOrderById(id);

        order.setQuantity(quantity);

        return order;
    }
}