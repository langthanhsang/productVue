package com.example.product.repository;

import com.example.product.entiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from product join\n" +
            "category on product.category_id = category.id \n" +
            "where category.id = ?", nativeQuery = true)
    Iterable<Product> findByCate(Long id);
}
