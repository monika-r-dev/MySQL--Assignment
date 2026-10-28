package com.example.foodorder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import com.example.foodorder.model.FoodOrder;
import com.example.foodorder.service.FoodOrderService;

@RestController
@RequestMapping("/orders")
@Validated
public class FoodOrderController {

    private final FoodOrderService foodOrderService;

    public FoodOrderController(FoodOrderService foodOrderService) {
        this.foodOrderService = foodOrderService;
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<FoodOrder>> getAllOrders() {

        return ResponseEntity.ok(
                foodOrderService.getAllOrders());
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getOrderById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                foodOrderService.getOrderById(id));
    }

    // Add order
    @PostMapping
    public ResponseEntity<FoodOrder> addOrder(
            @Valid @RequestBody FoodOrder order) {

        FoodOrder savedOrder =
                foodOrderService.addOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedOrder);
    }

    // Update complete order
    @PutMapping("/{id}")
    public ResponseEntity<FoodOrder> updateOrder(
            @PathVariable int id,
            @Valid @RequestBody FoodOrder order) {

        return ResponseEntity.ok(
                foodOrderService.updateOrder(id, order));
    }

    // Delete order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable int id) {

        foodOrderService.deleteOrder(id);

        return ResponseEntity.ok(
                "Order deleted successfully");
    }

    // Update quantity only
    @PatchMapping("/{id}")
    public ResponseEntity<FoodOrder> updateQuantity(
            @PathVariable int id,
            @RequestParam @Min(value = 1,
                message = "Quantity must be at least 1")
            int quantity) {

        return ResponseEntity.ok(
                foodOrderService.updateQuantity(id, quantity));
    }
}