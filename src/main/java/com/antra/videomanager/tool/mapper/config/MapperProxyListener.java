package com.antra.videomanager.tool.mapper.config;

import com.antra.videomanager.tool.mapper.Attribute;
import com.antra.videomanager.tool.mapper.MapperExecutor;
import com.antra.videomanager.tool.mapper.Mapping;
import com.antra.videomanager.tool.mapper.exception.NullTargetClassException;
import com.antra.videomanager.tool.mapper.support.SimpleMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapperProxyListener implements InvocationHandler{

    private Logger logger = LogManager.getLogger(MapperProxyListener.class);

    private MapperExecutor mapperExecutor;

    private Set<String> methodSet;

    void setMapperExecutor(MapperExecutor mapperExecutor) {
        methodSet = Stream.of(mapperExecutor.getClass().getMethods())
                .map(Method::getName)
                .collect(Collectors.toSet());
        this.mapperExecutor = mapperExecutor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!methodSet.contains(method.getName()) && (args == null || args.length == 0 || args.length > 1)) {
            logger.error("this method {} can't handle multi-parameters or parameter can't be empty", method.getName());
            throw new IllegalArgumentException("can't handle multi mapping or empty parameter, only access one parameter in this time");
        }
        Annotation[] annotations = method.getDeclaredAnnotations();
        Class<?> targetClazz = null;
        Map<String, String> fieldMapper = new HashMap<>();
        if(annotations != null && annotations.length != 0) {
            for(Annotation annotation: annotations) {
                if(annotation.annotationType().equals(Mapping.class)) {
                    logger.info("this method {} has annotation {}", method.getName(), annotation);
                    targetClazz = ((Mapping)annotation).target();
                    Attribute[] attributes = ((Mapping)annotation).value();
                    if(attributes.length == 1 && (attributes[0].to().equals("") || attributes[0].from().equals(""))) {
                        continue;
                    } else {
                        for(Attribute att: attributes) {
                            if(att.to().equals("") || att.from().equals("")) {
                                throw new IllegalArgumentException("can't set @Attribute with empty field name");
                            }
                            fieldMapper.put(att.to(), att.from());
                        }
                    }
                    break;
                }
            }
        }
        if(targetClazz == null && methodSet.contains(method.getName())) {
            return method.invoke(mapperExecutor, args);
        } else if(targetClazz == null) {
            logger.error("this method {} can't find target type, can't copy original object to target", method.getName());
            throw new NullTargetClassException("can't find target type, can't copy original object to target");
        }

        //custom interface query
        if(fieldMapper.size() == 0) {
            return ((SimpleMapper) mapperExecutor).copyOf(targetClazz, args[0]);
        } else {
            return ((SimpleMapper) mapperExecutor).copyOf(targetClazz, args[0], fieldMapper);
        }
    }

}

