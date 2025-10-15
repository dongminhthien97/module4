package com.example.shopping_cart.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {}
    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    // getter/setter chuẩn
    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    // ✅ Thêm getter alias để tương thích với template cart.items
    public Map<Product, Integer> getItems() {
        return products;
    }

    private boolean checkItemInCart(Product product){
        for (Entry<Product, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }

    private Entry<Product, Integer> selectItemInCart(Product product){
        for (Entry<Product, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product){
        if (!checkItemInCart(product)){
            products.put(product,1);
        } else {
            Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(),newQuantity);
        }
    }

    public void updateProductQuantity(Product product, int quantity){
        if (quantity <= 0){
            removeProduct(product);
            return;
        }
        Entry<Product, Integer> itemEntry = selectItemInCart(product);
        if (itemEntry != null) {
            products.replace(itemEntry.getKey(), quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public void removeProduct(Product product){
        Entry<Product, Integer> itemEntry = selectItemInCart(product);
        if (itemEntry != null) {
            products.remove(itemEntry.getKey());
        }
    }

    public Integer countProductQuantity(){
        int productQuantity = 0;
        for (Entry<Product, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity(){
        return products.size();
    }

    public Float countTotalPayment(){
        float payment = 0;
        for (Entry<Product, Integer> entry : products.entrySet()) {
            payment += entry.getKey().getPrice() * entry.getValue();
        }
        return payment;
    }

    public void updateProduct(Long id, int quantity) {
        for (Product p : products.keySet()) {
            if (p.getId().equals(id)) {
                if (quantity <= 0) {
                    products.remove(p);
                } else {
                    products.put(p, quantity);
                }
                break;
            }
        }
    }
}
