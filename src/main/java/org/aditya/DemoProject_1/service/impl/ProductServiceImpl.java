package org.aditya.DemoProject_1.service.impl;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.BusinessException;
import org.aditya.DemoProject_1.repository.ProductRepository;
import org.aditya.DemoProject_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    @Transactional
    public Product saveProduct(Product product) {
        // TODO Auto-generated method stub
        if(product.getName().isEmpty() || product.getName().length()==0)
            throw new BusinessException("601","Please provide a proper name, It is blank");
        try {

            return productRepository.save(product);
        }
        catch(IllegalArgumentException e)
        {
            throw new BusinessException("602","given product is null"+e.getMessage());
        }
        catch(Exception e)
        {
            throw new BusinessException("603","Something went wrong in business layer"+e.getMessage());
        }
        /*Product p=productRepository.save(product);
        if(p.getPrice()<10)
            throw new BusinessException("603","Something went wrong in business layer");
        if(p!=null)
            return p;
        return null;8*/
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        List<Product> empList=null;
        try {
            empList=productRepository.findAll();

        }
        catch(Exception e) {
            throw new BusinessException("605","Something went wrong in business layer"+e.getMessage());
        }
        if(empList.isEmpty())
            throw new BusinessException("604","List is empty");
        return empList;

    }

    @Override
    @Transactional
    public Product getProductById(long id) {
        try {
            return productRepository.findById(id).get();

        }
        catch(IllegalArgumentException e)
        {
            throw new BusinessException("606","given product is null"+e.getMessage());
        }
        catch(NoSuchElementException e) {
            throw new BusinessException("607","no such product exists "+e.getMessage());
        }
    }

    @Override
    @Transactional
    public Product updateProduct(long id, Product product) {
        Product existingProduct= productRepository.findById(id).orElseThrow(()->new BusinessException("609","No product exists by id "+id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        productRepository.save(existingProduct);

        return existingProduct;
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {

        Product employee= getProductById(id);

        try {
            productRepository.deleteById(id);
        }
        catch(IllegalArgumentException e) {
            throw new BusinessException("608","Please send a valid product id");
        }
    }
}
