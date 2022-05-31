package org.aditya.DemoProject_1.repository;

import org.aditya.DemoProject_1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
