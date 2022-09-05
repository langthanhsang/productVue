package com.example.product.service;

import com.example.product.entiy.Product;

import java.util.Optional;

public interface IProductService {

    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void delete(Long id);

    Iterable<Product> findByCategory(Long id);

}
