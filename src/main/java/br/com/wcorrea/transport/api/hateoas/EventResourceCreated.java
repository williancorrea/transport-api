package br.com.wcorrea.transport.api.hateoas;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * Creates the hateoas for a new child object in the system
 */
public class EventResourceCreated extends ApplicationEvent {

    private HttpServletResponse response;
    private Long id;

    public EventResourceCreated(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getId() {
        return id;
    }
}
