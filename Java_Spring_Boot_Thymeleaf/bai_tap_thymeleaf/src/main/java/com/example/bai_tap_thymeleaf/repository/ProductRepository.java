package com.example.bai_tap_thymeleaf.repository;

import com.example.bai_tap_thymeleaf.enity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository implements IProductRepository{
    private static final Map<Integer, Product> products;
    private static int nextId = 4;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "iPhone 13", 999.0, "Latest Apple phone", "Apple"));
        products.put(2, new Product(2, "Galaxy S22", 899.0, "Latest Samsung phone", "Samsung"));
        products.put(3, new Product(3, "MacBook Pro", 1999.0, "Powerful laptop", "Apple"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        if (product.getId() == 0) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
