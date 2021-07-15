package com.my.task.productcatalogadmin.repository;

import com.my.task.productcatalogadmin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByNameAndCategoryId(String name, Long categoryId);
}
