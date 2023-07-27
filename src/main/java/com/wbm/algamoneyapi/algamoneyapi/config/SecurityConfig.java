//package com.wbm.algamoneyapi.algamoneyapi.config;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //Extendendo a classe WebSecurityConfigurerAdapter ganhamos métodos que podem auxiliar na configuração
//    //vamos sobrescrevê-los e isso vai nos ajudar na config de segurança.
//
//    /**
//     * Configura a forma de autenticação, aqui no caso em memória.
//     * usando usuario admin e senha admin
//     * passamos um papel tbm (roles)
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("{noop}admin").roles("ROLE");
//    }
//
//    /**
//     * Aqui configuramos as rotas que serão autenticadas e as que não serão.
//     * configuramos a forma de autenticação tbm (exemplo basic / bearer etc
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/categorias").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic().and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .csrf().disable();
//    }
//}
