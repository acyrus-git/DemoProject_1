package org.aditya.DemoProject_1.service;

import org.aditya.DemoProject_1.controller.ProductController;
import org.aditya.DemoProject_1.entity.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(long id);

    Product updateProduct(long id, Product product);

    void deleteProduct(long id);


}
