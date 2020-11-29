package com.vslab.catalogue;

import com.vslab.catalogue.utils.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;


    @ExceptionHandler(CustomErrorResponse.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    @PostMapping(value = "/catalogue/", consumes = "application/json")
    public Object addProduct(@RequestBody Product product){
        try {
            return catalogueService.addProduct(product);
        }
        catch(CustomErrorResponse ce){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ce.getMessage());
        }
    }

    @DeleteMapping("catalogue/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId){
        catalogueService.deleteCategory(categoryId);
    }
}
