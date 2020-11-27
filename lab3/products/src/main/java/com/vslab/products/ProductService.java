package com.vslab.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    @Autowired
    private ProductRepo productRepo;

    public ProductService(){

    }

    public Product getProduct(Integer productId) {
        return null;
    }

    public void addProduct(Product product) {
    }

    public Product[] searchForProducts(String searchValue) {
    }

    public Product[] getAllProducts() {
    }

    public void updateProduct(Integer productId) {
    }

    public void deleteProduct(Integer productId) {
    }


    //TODO implement rest methods with database access
}
