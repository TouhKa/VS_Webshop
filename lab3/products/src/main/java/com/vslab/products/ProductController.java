package com.vslab.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {

     @Autowired
     private ProductService productService;

    //TODO mapping requests to service

     @RequestMapping(value = "/product/", method = RequestMethod.GET)
     public Product[] getProducts(@RequestParam (value= "searParam", defaultValue = "", required = false) String searchValue){
          if (searchValue==""){
               return this.productService.getAllProducts();
          }else{
               return this.productService.searchForProducts(searchValue);
          }
     }

     @RequestMapping(value = "/product/", method = RequestMethod.POST)
     public void addProduct(@RequestBody Product product){
          this.productService.addProduct(product);
     }


     @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
     public Product getProduct(@PathVariable Integer productId){
          return this.productService.getProduct(productId);
     }

     @RequestMapping(value = "/product/{productId}", method = RequestMethod.PUT)
     public void UpdateProduct(@PathVariable Integer productId){
          this.productService.updateProduct(productId);
     }

     //delete
     @RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
     public void deleteProduct(@PathVariable Integer productId) {
          this.productService.deleteProduct(productId);

     }


}
