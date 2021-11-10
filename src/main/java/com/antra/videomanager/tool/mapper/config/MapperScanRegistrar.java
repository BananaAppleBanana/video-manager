package com.antra.videomanager.tool.mapper.config;

import com.antra.videomanager.tool.mapper.Mapper;
import com.antra.videomanager.tool.mapper.MapperScan;
import com.antra.videomanager.tool.mapper.support.SimpleMapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;

@Configuration
public class MapperScanRegistrar implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware {

    private ClassLoader classLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // Get the MyInterfaceScan annotation attributes
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(MapperScan.class.getCanonicalName());

        if (annotationAttributes != null) {
            String[] domainPackages = (String[]) annotationAttributes.get("value");
            String[] basePackages = new String[]{((StandardAnnotationMetadata) metadata).getIntrospectedClass().getPackage().getName()};

            // using these packages, scan for interface annotated with @Mapper and pojo classes
            ClassPathScanningCandidateComponentProvider mapperProvider = getMapperProvider();
            ClassPathScanningCandidateComponentProvider pojoProvider = getPOJOProvider();

            // Scan all pojo packages
            for (String domainPackage : domainPackages) {
                for (BeanDefinition beanDefinition : pojoProvider.findCandidateComponents(domainPackage)) {
                    String name = beanDefinition.getBeanClassName();
                    try {
                        Class<?> clazz = Class.forName(name);
                        SimpleMapper.addBuiltInPOJO(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Scan all packages for @mapper
            for (String basePackage : basePackages) {
                for (BeanDefinition beanDefinition : mapperProvider.findCandidateComponents(basePackage)) {
                    String name = beanDefinition.getBeanClassName();
                    try {
                        Class<?> clazz = Class.forName(name);
                        //Object mapperInstance = Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new MapperProxyListener());

                        GenericBeanDefinition proxyBeanDefinition = new GenericBeanDefinition();
                        proxyBeanDefinition.setBeanClass(clazz);

                        proxyBeanDefinition.setAutowireMode( GenericBeanDefinition.AUTOWIRE_CONSTRUCTOR );
                        proxyBeanDefinition.setScope( BeanDefinition.SCOPE_SINGLETON );

                        ConstructorArgumentValues args = new ConstructorArgumentValues();

                        args.addGenericArgumentValue(classLoader);
                        args.addGenericArgumentValue(clazz);
                        proxyBeanDefinition.setConstructorArgumentValues(args);

                        proxyBeanDefinition.setFactoryBeanName("mapperProxyBeanFactory");
                        proxyBeanDefinition.setFactoryMethodName("createMapperProxyBean");

                        registry.registerBeanDefinition(name, proxyBeanDefinition);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private ClassPathScanningCandidateComponentProvider getMapperProvider() {
        ClassPathScanningCandidateComponentProvider mapperProvider = new ClassPathScanningCandidateComponentProvider(false){
            // Override isCandidateComponent to only scan for interface
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                AnnotationMetadata metadata = beanDefinition.getMetadata();
                return metadata.isIndependent() && metadata.isInterface();
            }
        };
        mapperProvider.addIncludeFilter(new AnnotationTypeFilter(Mapper.class));
        return mapperProvider;
    }

    private ClassPathScanningCandidateComponentProvider getPOJOProvider() {
        ClassPathScanningCandidateComponentProvider pojoProvider = new ClassPathScanningCandidateComponentProvider(false){
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                AnnotationMetadata metadata = beanDefinition.getMetadata();
                return metadata.isConcrete();
            }
        };
        pojoProvider.addIncludeFilter((metadataReader, metadataReaderFactory) -> metadataReader.getClassMetadata().isConcrete());
        return pojoProvider;
    }

}