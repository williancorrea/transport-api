package br.com.wcorrea.transport.api.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class ContextHelper implements ApplicationContextAware{

    private static final ContextHelper INSTANCE = new ContextHelper();
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getCurrentApplicationContext(){
        return INSTANCE.applicationContext;
    };

    public static ContextHelper getInstance(){
        return INSTANCE;
    }

    private ContextHelper(){
    }

}