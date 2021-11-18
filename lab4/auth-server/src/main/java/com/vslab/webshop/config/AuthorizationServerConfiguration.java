package com.vslab.webshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("${webshop-oauth.clientId}")
    private String clientId;
    @Value("${webshop-oauth.secret}")
    private String clientSecret;
    @Value("${webshop-oauth.jwt-signing-key}")
    private String JWTSigningKey;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .authorizedGrantTypes("password", "client_credentials")
                .scopes("user_info", "role_info", "product_info", "category_info", "catalogue_info")
                .autoApprove(true)
                .and()
                .withClient("user-service-client")
                .secret(passwordEncoder.encode("user-service-secret"))
                .authorizedGrantTypes("client_credentials", "password")
                .scopes("user_info")
                .autoApprove(true)
                .and()
                .withClient("role-service-client")
                .secret(passwordEncoder.encode("role-service-secret"))
                .authorizedGrantTypes("client_credentials")
                .scopes("role_info")
                .autoApprove(true)
                .and()
                .withClient("product-service-client")
                .secret(passwordEncoder.encode("product-service-secret"))
                .authorizedGrantTypes("client_credentials")
                .scopes("product_info")
                .autoApprove(true)
                .and()
                .withClient("category-service-client")
                .secret(passwordEncoder.encode("category-service-secret"))
                .authorizedGrantTypes("client_credentials")
                .scopes("category_info")
                .autoApprove(true);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JWTSigningKey);
        return converter;
    }
}
