package com.vslab.webshop.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vslab.webshop.CatalogueService;
import com.vslab.webshop.product.Product;
import com.vslab.webshop.utils.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableHystrix
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;


    @ExceptionHandler(CustomErrorResponse.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    @PostMapping(value = "/catalogue/", consumes = "application/json")
    @HystrixCommand(fallbackMethod = "indicateUnknownEntity")
    public Product addProduct(@RequestBody Product product){
            return catalogueService.addProduct(product);
    }

    @DeleteMapping("catalogue/{categoryId}")
    @HystrixCommand(fallbackMethod = "indicateUnknownEntity")
    public String deleteCategory(@PathVariable int categoryId){
        catalogueService.deleteCategory(categoryId);
        return "Succesfully deleted Category";
    }

    public String indicateUnknownEntity(int categorieID){
        return "Ressource not found";
    }

    public Product indicateUnknownEntity(Product product){
        return new Product();
    }
}
