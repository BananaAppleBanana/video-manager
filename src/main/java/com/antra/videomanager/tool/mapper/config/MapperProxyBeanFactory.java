package com.antra.videomanager.tool.mapper.config;

import com.antra.videomanager.tool.mapper.MapperExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;


public class MapperProxyBeanFactory {

    @Autowired
    private MapperProxyListener mapperProxyListener;

    @Autowired
    private MapperExecutor mapperExecutor;

    @SuppressWarnings("unchecked")
    public <T> T createMapperProxyBean(ClassLoader classLoader, Class<T> clazz) {
        mapperProxyListener.setMapperExecutor(mapperExecutor);
        return (T)Proxy.newProxyInstance(classLoader, new Class[] {clazz}, mapperProxyListener);
    }

}
