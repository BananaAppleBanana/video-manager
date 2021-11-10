package com.antra.videomanager.tool.mapper;


import com.antra.videomanager.tool.mapper.config.MapperConfig;
import com.antra.videomanager.tool.mapper.config.MapperScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({
        MapperConfig.class,
        MapperScanRegistrar.class
})
public @interface MapperScan {

    String[] value() default {};
}
