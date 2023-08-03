//package com.wbm.algamoneyapi.algamoneyapi.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
////    @Autowired
////    private UserDetailsService userDetailsService;
//
//    // @formatter:off
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                    .withClient("angular")
//                    .secret("$2a$10$G1j5Rf8aEEiGc/AET9BA..xRR.qCpOUzBZoJd8ygbGy6tb3jsMT9G") // @ngul@r0
//                    .scopes("read", "write")
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .accessTokenValiditySeconds(1800)
//                    .refreshTokenValiditySeconds(3600 * 24)
//                .and()
//                     .withClient("mobile")
//                     .secret("$2a$10$G1j5Rf8aEEiGc/AET9BA..xRR.qCpOUzBZoJd8ygbGy6tb3jsMT9G") // @ngul@r0
//                     .scopes("read")
//                     .authorizedGrantTypes("password", "refresh_token")
//                     .accessTokenValiditySeconds(1800)
//                     .refreshTokenValiditySeconds(3600 * 24);
//    }
//    // @formatter:on
//
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .authenticationManager(authenticationManager)
//                .accessTokenConverter(accessTokenConverter())
//                .tokenStore(tokenStore());
//    }
//
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.checkTokenAccess("permitAll()");
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//
//        //Tem que ter pelo menos 256 bits de tamanho !
//        accessTokenConverter.setSigningKey("abcdefghijklmnopqrstuvwxzy01234567");
//
//        return accessTokenConverter;
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//}
