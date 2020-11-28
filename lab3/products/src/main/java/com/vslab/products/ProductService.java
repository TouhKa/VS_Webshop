package com.vslab.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ProductService(){
    }

    public Product getProduct(Integer productId) {
        return productRepo.findById(productId);
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public Product[] getAllProducts(Optional<String> searchValue, Optional<String> priceMinValue, Optional<String> priceMaxValue) {
        Product[] products = getAllProducts();
        List<Product> foundProducts = new ArrayList<Product>();
        for (Product p : products){
            if (searchValue.isPresent() && priceMaxValue.isPresent() && priceMinValue.isPresent()) {
                String searchParam= searchValue.get().toLowerCase(); //can be name or details-> check if contains searchparam

                double maxPrice = Float.parseFloat(priceMaxValue.get());
                double minPrice = Float.parseFloat(priceMinValue.get());
                if(p.getPrice()>= minPrice){
                    if(p.getPrice() <= maxPrice){
                        if (p.getDetails().toLowerCase().contains(searchParam)){
                            foundProducts.add(p);
                        }
                    }
                }
            }else if(searchValue.isPresent() && !priceMaxValue.isPresent() && priceMinValue.isPresent()) {
                String searchParam= searchValue.get().toLowerCase();
                double minPrice = Float.parseFloat(priceMinValue.get());
                if(p.getPrice()>= minPrice){
                    if (p.getDetails().toLowerCase().contains(searchParam)){
                        foundProducts.add(p);
                    }
                }
            }else if (searchValue.isPresent() && priceMaxValue.isPresent() && !priceMinValue.isPresent()) {
                String searchParam = searchValue.get().toLowerCase();
                double maxPrice = Float.parseFloat(priceMaxValue.get());
                if(p.getPrice() <= maxPrice){
                    if (p.getDetails().toLowerCase().contains(searchParam)){
                        foundProducts.add(p);
                    }
                }
            } else if (searchValue.isPresent() && !priceMaxValue.isPresent() && !priceMinValue.isPresent()) {
                String searchParam = searchValue.get().toLowerCase();
                if (p.getDetails().toLowerCase().contains(searchParam)){
                    foundProducts.add(p);
                }
            } else if (!searchValue.isPresent() && !priceMaxValue.isPresent() && priceMinValue.isPresent()) {
                double minPrice = Float.parseFloat(priceMinValue.get());
                if(p.getPrice()>= minPrice){
                    foundProducts.add(p);
                }
            } else if (!searchValue.isPresent() && priceMaxValue.isPresent() && priceMinValue.isPresent()) {
                double maxPrice = Float.parseFloat(priceMaxValue.get());
                double minPrice = Float.parseFloat(priceMinValue.get());
                if(p.getPrice()>= minPrice) {
                    if (p.getPrice() <= maxPrice) {
                        foundProducts.add(p);
                    }
                }
            } else if (!searchValue.isPresent() && priceMaxValue.isPresent() && !priceMinValue.isPresent()) {
                double maxPrice = Float.parseFloat(priceMaxValue.get());
                if(p.getPrice() <= maxPrice){
                    foundProducts.add(p);
                }
            } else {
                return products;
            }
        }

        Product[] finalFoundProducts = new Product[foundProducts.size()];
        return foundProducts.toArray(finalFoundProducts);
    }

    public Product[] getAllProducts() {
        List<Product> list = productRepo.findAll();
        Product[] products = new Product[list.size()];
        return list.toArray(products);
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(Integer productId) {
        productRepo.deleteById(productId);
    }
}
