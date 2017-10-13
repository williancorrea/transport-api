package br.com.wcorrea.transport.api.hateoas.listener;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class EventResourceListener implements ApplicationListener<EventResourceCreated> {

    @Override
    public void onApplicationEvent(EventResourceCreated eventResourceCreated) {
        HttpServletResponse response = eventResourceCreated.getResponse();
        adicionarHeaderLocation(response, eventResourceCreated.getKey());
    }

    private void adicionarHeaderLocation(HttpServletResponse response, String key) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{key}").buildAndExpand(key).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
