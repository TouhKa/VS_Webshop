package com.vslab.webshop.web;

import com.vslab.webshop.Product;
import com.vslab.webshop.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {

     @Autowired
     private ProductService productService;

     @GetMapping("/product/")
     public Product[] getProducts(@RequestParam(value= "searchValue", required = false) Optional<String> searchValue,
                                  @RequestParam(value = "priceMinValue", required = false) Optional<String> priceMinValue,
                                  @RequestParam(value = "priceMaxValue", required = false) Optional<String> priceMaxValue){
          if (searchValue.isEmpty() && priceMinValue.isEmpty() && priceMaxValue.isEmpty()){
               return this.productService.getAllProducts();
          }else{

               return this.productService.getAllProducts(searchValue, priceMinValue, priceMaxValue);
          }
     }

     @PostMapping("/product/")
     public Product addProduct(@RequestBody Product product){
          return this.productService.addProduct(product);
     }


     @GetMapping("/product/{productId}")
     public Product getProduct(@PathVariable int productId){
          return this.productService.getProduct(productId);
     }


     @PutMapping("/product/")
     public void UpdateProduct(@RequestBody Product product){
          this.productService.updateProduct(product);
     }

     @DeleteMapping("/product/{productId}")
     public void deleteProduct(@PathVariable int productId) {
          this.productService.deleteProduct(productId);

     }
}
