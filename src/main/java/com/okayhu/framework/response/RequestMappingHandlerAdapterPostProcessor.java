package com.okayhu.framework.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collections;

/**
 * 将 WrapperResponseAdvice 注册到 RequestMappingHandlerAdapter
 *
 * @author okayhu
 * @date 2022/1/19
 */
@Slf4j
public class RequestMappingHandlerAdapterPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private ObjectMapper objectMapper;

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            log.info("Set WrapperResponseAdvice to RequestMappingHandlerAdapter");
            ResponseWrapperAdvice responseWrapperAdvice = new ResponseWrapperAdvice(objectMapper);
            ((RequestMappingHandlerAdapter) bean).setResponseBodyAdvice(Collections.singletonList(responseWrapperAdvice));
        }
        return bean;
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        this.objectMapper = beanFactory.getBean(ObjectMapper.class);
    }
}
