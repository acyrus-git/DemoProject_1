package org.aditya.DemoProject_1.service.impl;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.repository.ProductRepository;
import org.aditya.DemoProject_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product saveProduct(Product product) {
        // TODO Auto-generated method stub
        Product product1=null;

            product1=productRepository.save(product);


        return product1;

    }
}
