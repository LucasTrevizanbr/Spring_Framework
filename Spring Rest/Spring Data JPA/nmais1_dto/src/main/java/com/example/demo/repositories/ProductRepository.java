package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p JOIN FETCH p.categories WHERE p IN :produtos")
    List<Product> buscarCategoriaProdutos(List<Product> produtos);

}
