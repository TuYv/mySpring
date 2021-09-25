package com.max.springframework.beans.factory;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.PropertyValue;
import com.max.springframework.beans.PropertyValues;
import com.max.springframework.beans.factory.config.BeanDefinition;
import com.max.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.max.springframework.core.io.DefaultResourceLoader;
import com.max.springframework.core.io.Resource;
import com.max.springframework.util.StringValueResolver;
import java.io.IOException;
import java.util.Properties;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-24 11:07
 **/
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
        throws BeansException {
        try {
            //加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);

            //占位符替换属性值
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for(String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if(!(value instanceof String)) continue;
                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));

                }
            }

            //向容器中添加字符串解析器 供解析@Value注解使用
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = (String) value;
        StringBuilder builder = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            builder.replace(startIdx, stopIdx + 1, propVal);
        }
        return builder.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }

    }
}
