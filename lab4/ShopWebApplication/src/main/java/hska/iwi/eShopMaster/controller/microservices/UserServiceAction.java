package hska.iwi.eShopMaster.controller.microservices;

import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.MicroUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@SuppressWarnings("deprecation")
public class UserServiceAction {
    private String authServerTokenURL = "http://auth-server:8092/oauth/token";
    private String userServiceURL = "http://zuul:8091/users";
    private String clientId = "webshop-client";
    private String clientSecret = "webshop-secret";
    private String userServiceScope = "user_info";

    // Create a Logger
    private static final Logger log = LoggerFactory.getLogger(UserServiceAction.class);

    //pass client credentials to corresponding service
    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(authServerTokenURL);
//        details.setGrantType("client_credentials");

        List<String> scope = new ArrayList<>();
        scope.add(userServiceScope);
        details.setScope(scope);

        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1"); //is this needed
        details.setTokenName("User_Service");
        return details;
    }

    @Bean(name = "restTemplate") // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate restTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

    //methods are named the same as of UserService.java from the corresponding module

    public MicroUser[] getAllUsers() {
        OAuth2RestTemplate restTemplate = restTemplate();
        log.info(restTemplate.getAccessToken().getValue());
        MicroUser[] users = restTemplate.getForObject(userServiceURL, MicroUser[].class);
        for(MicroUser user : users ) {
            log.warn(users.toString());
        }
        return users;

    }


    public MicroUser addUser(MicroUser microUser, OAuth2RestTemplate restTemplate) {
        return restTemplate.postForObject(userServiceURL, microUser, MicroUser.class) ;
    }

    public MicroUser getUserById(String id, OAuth2RestTemplate restTemplate) {
        return restTemplate.getForObject(userServiceURL + "/" + id, MicroUser.class);
    }

    public MicroUser updateUser(MicroUser microUser, OAuth2RestTemplate restTemplate) {
        restTemplate.put(userServiceURL, microUser);

        //todo remove
        return restTemplate.getForObject(userServiceURL + "/" + microUser.getId(), MicroUser.class);
    }

    public void deleteUser(String id, OAuth2RestTemplate restTemplate) {
        restTemplate.delete(userServiceURL + "/" + id);
    }

}
