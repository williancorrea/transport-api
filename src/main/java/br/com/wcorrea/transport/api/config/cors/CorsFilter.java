package br.com.wcorrea.transport.api.config.cors;

import br.com.wcorrea.transport.api.config.property.ApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Autowired
    private ApiProperty apiProperty;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (apiProperty.getSecurity().isEnableCors()) {
            response.setHeader("Access-Control-Allow-Origin", apiProperty.getOriginAllowed().trim());
            response.setHeader("Access-Control-Allow-Credentials", "true");

            //Caso nao tenha a origem permitida
            if (!apiProperty.getOriginAllowed().trim().equals(request.getHeader("Origin").trim())) {
                chain.doFilter(req, resp);
            }
        } else {
            //Permite acesso de qualquer lugar
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin").trim());
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }

        if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            response.setHeader("Access-Control-Max-Age", "3600");

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
