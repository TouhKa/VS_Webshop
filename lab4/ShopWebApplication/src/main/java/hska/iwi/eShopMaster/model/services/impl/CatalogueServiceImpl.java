package hska.iwi.eShopMaster.model.services.impl;

import hska.iwi.eShopMaster.model.data.objects.Category;
import hska.iwi.eShopMaster.model.data.objects.Product;
import hska.iwi.eShopMaster.model.services.CatalogueService;
import hska.iwi.eShopMaster.model.services.CategoryService;
import hska.iwi.eShopMaster.model.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.ArrayList;
import java.util.List;

public class CatalogueServiceImpl implements CatalogueService {
    
    private final ProductService productService;
    
    private final CategoryService categoryService;
    
    public CatalogueServiceImpl() {
        this.productService = new ProductServiceImpl();
        this.categoryService = new CategoryServiceImpl();
    }
    
    @Override
    public Product addProduct(Product product) {
        for (Category category : categoryService.getAll()) {
            if (product.getCategoryId() == category.getId()) {
                productService.addProduct(product);
                return product;
            }
        }
        // in case no category had the same ID we return null
        return null;
    }
    
    @Override
    public void deleteCategory(int id) {
        // first delete all products that have this category id
        for (Product product : productService.getAllProducts()) {
            if (product.getCategoryId() == id) {
                productService.deleteProduct(id);
            }
        }
        // then we delete the category
        categoryService.deleteCategory(id);
    }
}
