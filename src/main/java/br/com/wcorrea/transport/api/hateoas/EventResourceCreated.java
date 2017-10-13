package br.com.wcorrea.transport.api.hateoas;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * Creates the hateoas for a new child object in the system
 */
public class EventResourceCreated extends ApplicationEvent {

    private HttpServletResponse response;
    private String key;

    public EventResourceCreated(Object source, HttpServletResponse response, String key) {
        super(source);
        this.response = response;
        this.key = key;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public String getKey() {
        return key;
    }
}
