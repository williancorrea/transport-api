package br.com.wcorrea.transport.api;

import br.com.wcorrea.transport.api.config.AutowireHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    /**
     * Corrige o timezone da datas
     */
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
//    TimeZone.setDefault(TimeZone.getTimeZone("+3:00")); // PODE TB SER USADO ASSIM
    }

    /**
     * Autowired Personalizado dentro das classes de Modelo
     *
     * @return
     */
    @Bean
    public AutowireHelper autowireHelper() {
        return AutowireHelper.getInstance();
    }
}
