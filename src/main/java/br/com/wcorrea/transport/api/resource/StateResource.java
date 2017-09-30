package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible for providing all the resources needed to handle country trash
 */
@RestController
@RequestMapping("/states")
public class StateResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private StateRepository stateRepository;


}
