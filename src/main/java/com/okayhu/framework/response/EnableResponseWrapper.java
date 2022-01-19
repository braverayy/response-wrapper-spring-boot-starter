package com.okayhu.framework.response;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启包装响应功能
 *
 * @author okayhu
 * @date 2022/1/19
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RequestMappingHandlerAdapterPostProcessor.class)
public @interface EnableResponseWrapper {

    Class<?> value() default ResponseEntity.class;
}
