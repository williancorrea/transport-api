package br.com.wcorrea.transport.api;

import br.com.wcorrea.transport.api.config.AutowireHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public AutowireHelper autowireHelper() {
        return AutowireHelper.getInstance();
    }
}
