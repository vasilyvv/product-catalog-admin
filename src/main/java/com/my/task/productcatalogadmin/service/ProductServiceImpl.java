package com.my.task.productcatalogadmin.service;

import com.my.task.productcatalogadmin.exception.ResourceNotFoundException;
import com.my.task.productcatalogadmin.model.Product;
import com.my.task.productcatalogadmin.model.ProductFilter;
import com.my.task.productcatalogadmin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findProducts(ProductFilter productFilter) {
        if (productFilter.getName() != null && productFilter.getCategoryId() != null) {
            return productRepository.findByNameAndCategoryId(
                    productFilter.getName(), productFilter.getCategoryId());
        }

        if (productFilter.getName() != null) {
            return productRepository.findByName(productFilter.getName());
        }

        if (productFilter.getCategoryId() != null) {
            return productRepository.findByCategoryId(productFilter.getCategoryId());
        }

        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreateDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product currentProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist with id : " + id));

        currentProduct.setName(productDetails.getName());
        currentProduct.setDescription(productDetails.getDescription());
        currentProduct.setPrice(productDetails.getPrice());
        currentProduct.setImagePath(productDetails.getImagePath());
        currentProduct.setCategoryId(productDetails.getCategoryId());
        currentProduct.setStatus(productDetails.getStatus());

        return productRepository.save(currentProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product currentProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist with id : " + id));

        productRepository.delete(currentProduct);
    }
}
