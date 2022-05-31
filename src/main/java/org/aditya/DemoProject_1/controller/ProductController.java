package org.aditya.DemoProject_1.controller;

import org.aditya.DemoProject_1.entity.Product;
import org.aditya.DemoProject_1.exception.BusinessException;
import org.aditya.DemoProject_1.exception.ControllerException;
import org.aditya.DemoProject_1.service.MyTask3;
import org.aditya.DemoProject_1.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    Logger logger= LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<?> saveHotel(@RequestBody  Product product){
        try {
                logger.info("Request recieved to add product with name " + product.getName() + " and price " + product.getPrice());
                return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
        } catch(BusinessException e) {
            ControllerException ce= new ControllerException(e.getErrorCode(),e.getMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
		catch(Exception e) {
            ControllerException ce= new ControllerException("609","Something wrong went in controller layer");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping( value="/get-products")
    public ResponseEntity<?> getAllProducts(){
        //logger.trace("fatal error");

        try {
            return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
        }
        catch (BusinessException e)
        {
            ControllerException ce= new ControllerException(e.getErrorCode(),e.getMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);

        }
        catch(Exception e) {
            ControllerException ce= new ControllerException("609","Something wrong went in controller layer");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }



    }
    @GetMapping("product/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long id){
        try {
            return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
        }
        catch(BusinessException e)
        {
            ControllerException ce= new ControllerException(e.getErrorCode(),e.getMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
        catch(Exception e)
        {
            ControllerException ce= new ControllerException("610","Something wrong went in controller layer");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable long id,@RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id){

        productService.deleteProduct(id);

        return new ResponseEntity<>("Product deleted successfully" , HttpStatus.OK);
    }
}
