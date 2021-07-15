package com.my.task.productcatalogadmin.service;

import com.my.task.productcatalogadmin.model.Product;
import com.my.task.productcatalogadmin.model.ProductFilter;

import java.util.List;

public interface ProductService {

    List<Product> findProducts(ProductFilter productFilter);

    Product createProduct(Product productDetails);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
