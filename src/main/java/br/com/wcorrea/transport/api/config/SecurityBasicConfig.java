package br.com.wcorrea.transport.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("basic-security")
@EnableWebSecurity
public class SecurityBasicConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TODO: REMOVER ACESSO PUBLICO E COLOCAR AS PERMISSOES
        http.authorizeRequests()


                .antMatchers("/fretamentosEventuais/**").permitAll()
                .antMatchers("/veiculos/**").permitAll()
                .antMatchers("/pessoas/**").permitAll()
                .antMatchers("/cidades/**").permitAll()
                .antMatchers("/veiculos/**").permitAll()
                .antMatchers("/veiculosMarca/**").permitAll()
                .antMatchers("/veiculosModelo/**").permitAll()
                .antMatchers("/combustivel/**").permitAll()

                .antMatchers("/financeiro/plano-conta/**").permitAll()
                .antMatchers("/financeiro/cheques-recebidos/**").permitAll()
                .antMatchers("/financeiro/bancos/**").permitAll()
                .antMatchers("/financeiro/bancos-agencias/**").permitAll()
                .antMatchers("/financeiro/bancos-contas/**").permitAll()
                .antMatchers("/financeiro/bancos-extrato-abreviacao/**").permitAll()
                .antMatchers("/financeiro/recebimento-tipo/**").permitAll()
                .antMatchers("/financeiro/recebimento-lancamentos/**").permitAll()
                .antMatchers("/financeiro/recebimento-parcela/**").permitAll()
                .antMatchers("/financeiro/recebimento-parcela-detalhe/**").permitAll()
                .antMatchers("/financeiro/recebimento-parcela-status/**").permitAll()


                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

}

